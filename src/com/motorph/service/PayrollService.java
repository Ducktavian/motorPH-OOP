package com.motorph.service;

import com.motorph.exception.UnauthorizedException;
import com.motorph.model.DeductionBreakdown;
import com.motorph.model.Employee;
import com.motorph.model.Payslip;
import com.motorph.model.Role;
import com.motorph.model.UserAccount;
import com.motorph.util.Session;

import java.time.LocalDate;


public class PayrollService {
    
    private AttendanceService attendanceService;
    private RateService rateService;
    private DeductionService deductionService;

    // Constructor
    public PayrollService(AttendanceService attendanceService, RateService rateService) {
        this.attendanceService = attendanceService;
        this.rateService = rateService;
    }
    
    // Constructor
    public PayrollService(AttendanceService attendanceService, RateService rateService, DeductionService deductionService) {
        this.attendanceService = attendanceService;
        this.rateService = rateService;
        this.deductionService = deductionService;
    }
    
    // Security
    public void processPayroll() {
        UserAccount currentUser = Session.getCurrentUser();
        
        if (currentUser == null) {
            throw new UnauthorizedException("No active session");
        }
        
        // Use Enum Role
        if (currentUser.getRole() != Role.FINANCE) {
            throw new UnauthorizedException("Only Finance can process payroll.");
        }
        
        System.out.println("Security check passed. Processing payroll");
    }
    
    
    
    // Generate a payslip for a given payroll period
    public Payslip generatePayslip(Employee employee, LocalDate periodStart, LocalDate periodEnd) {
        
        // get cutoffHours
        double cutoffHours = attendanceService.computeTotalHours(employee.getEmployeeNumber(), periodStart, periodEnd);
        // get hourly rate
        double hourlyRate = rateService.computeHourlyRate(employee);
        // derive gross pay
        double cutoffGross = round(cutoffHours * hourlyRate);
        
        // get allowances
        double allowances = round(employee.getTotalAllowances() / 2);
        
        
        // Compute MONTHLY gross for deductions
        LocalDate monthStart = periodStart.withDayOfMonth(1);
        LocalDate monthEnd = periodStart.withDayOfMonth(periodStart.lengthOfMonth());

        double monthlyHours = attendanceService.computeTotalHours(employee.getEmployeeNumber(), monthStart, monthEnd);
        
        double monthlyGross = round(monthlyHours * hourlyRate);
        
        // // Compute deductions
        DeductionBreakdown deductionBreakdown = null;
        double totalDeductions = 0;
        
        // If second cutoff: Apply deductions
        if (isSecondCutoff(periodEnd)) {
            deductionBreakdown = computeMonthlyDeductions(employee, monthlyGross);
            totalDeductions = deductionBreakdown.getTotal();
        }
       
        // Compute Netpay
        double netPay = cutoffGross + allowances - totalDeductions;
        
        // Return Payslip object
        return new Payslip(
                employee.getEmployeeNumber(),
                employee.getFullName(),
                employee.getPosition(),
                periodStart,
                periodEnd,
                cutoffHours,
                hourlyRate,
                cutoffGross,
                allowances,
                deductionBreakdown,
                netPay
        );
    }
    
    
    public DeductionBreakdown computeMonthlyDeductions(Employee emp, double monthlyGross) {
        
        // Monthly contributions
        double monthlySSS = deductionService.calculateSSSContribution(monthlyGross);
        double monthlyPhilHealth = deductionService.calculatePhilHealthContribution(monthlyGross);
        double monthlyPagIbig = deductionService.calculatePagIbigContribution(monthlyGross);
        
        // Calculate monthlyTaxableIncome
        double monthlyTaxableIncome = monthlyGross - monthlySSS - monthlyPhilHealth - monthlyPagIbig;
        // Calculate tax
        double tax = deductionService.calculateTax(monthlyTaxableIncome);
        
        // Return DeductionBreakdown object
        return new DeductionBreakdown(
                round(monthlySSS),
                round(monthlyPhilHealth),
                round(monthlyPagIbig),
                round(tax)
        );
    }
    
    // Helper
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
    
    private boolean isSecondCutoff(LocalDate periodEnd) {
        return periodEnd.getDayOfMonth() == periodEnd.lengthOfMonth();
    }
}

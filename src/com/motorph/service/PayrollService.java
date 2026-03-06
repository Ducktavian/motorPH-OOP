package com.motorph.service;

import com.motorph.exception.UnauthorizedException;
import com.motorph.model.DeductionBreakdown;
import com.motorph.model.DeductionRule;
import com.motorph.model.Employee;
import com.motorph.model.PagIbigDeduction;
import com.motorph.model.Payslip;
import com.motorph.model.PhilHealthDeduction;
import com.motorph.model.Role;
import com.motorph.model.SSSDeduction;
import com.motorph.model.TaxDeduction;
import com.motorph.model.UserAccount;
import com.motorph.util.Session;

import java.time.LocalDate;


public class PayrollService {
    
    private AttendanceService attendanceService;
    private RateService rateService;
    private DeductionService deductionService;

    // Constructor
    public PayrollService() {}
    
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
    
   
    
    // Computes all time gross pay based on hours worked and hourly rate
    public double computeGrossPay(Employee employee) {
        
        // Totalhours worked in attendance record
        double totalHours = attendanceService.computeTotalHours(employee.getEmployeeNumber());
        
        double hourlyRate = rateService.computeHourlyRate(employee);
        
        double grossPay = totalHours * hourlyRate;
        
        // 739291.0 vs 739290.51
        return round(grossPay);
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
        double allowances = employee.getTotalAllowances();
        
        
        // Compute MONTHLY gross for deductions
        LocalDate monthStart = periodStart.withDayOfMonth(1);
        LocalDate monthEnd = periodStart.withDayOfMonth(periodStart.lengthOfMonth());

        double monthlyHours = attendanceService.computeTotalHours(employee.getEmployeeNumber(), monthStart, monthEnd);
        
        double monthlyGross = round(monthlyHours * hourlyRate);
        
        // Compute deductions
        DeductionBreakdown deductions = computeSemiMonthlyDeductions(employee, monthlyGross);
        double totalDeductions = deductions.getTotal();
       
        // Compute Netpay
        double netPay = cutoffGross + allowances - totalDeductions;
        
        // Return Payslip object
        return new Payslip(
                "PLACEHOLDER",
                employee.getEmployeeNumber(),
                employee.getFirstName() + " " + employee.getLastName(),
                employee.getPosition(),
                periodStart,
                periodEnd,
                cutoffHours,
                hourlyRate,
                cutoffGross,
                allowances,
                totalDeductions,
                netPay
        );
    }
    
    
    public DeductionBreakdown computeSemiMonthlyDeductions(Employee emp, double monthlyGross) {
        
        double basicSalary = emp.getBasicSalary();
        

        
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
                round(monthlySSS / 2),
                round(monthlyPhilHealth / 2),
                round(monthlyPagIbig / 2),
                round(tax / 2)
        );
    }
    
    // Helper
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}

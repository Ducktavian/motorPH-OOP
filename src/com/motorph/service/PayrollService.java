/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.service;

import com.motorph.model.Employee;
import com.motorph.model.Payslip;

import java.time.LocalDate;

/**
 *
 * @author Lenovo
 */
public class PayrollService {
    
    private AttendanceService attendanceService;
    private RateService rateService;
    
    // Constructor
    public PayrollService(AttendanceService attendanceService, RateService rateService) {
        this.attendanceService = attendanceService;
        this.rateService = rateService;
    }
    
    // Computes gross pay based on hours worked and hourly rate
    
    public double computeGrossPay(Employee employee) {
        
        // Totalhours worked in attendance record
        double totalHours = attendanceService.computeTotalHours(employee.getEmployeeNumber());
        
        double hourlyRate = rateService.computeHourlyRate(employee);
        
        double grossPay = totalHours * hourlyRate;
        
        // 739291.0 vs 739290.51
        return Math.round(grossPay * 100.0) / 100.0;
    }
    
    // Generate a payslip for a given payroll period
    public Payslip generatePayslip(Employee employee, LocalDate periodStart, LocalDate periodEnd) {
        
        // get total hours
        double totalHours = attendanceService.computeTotalHours(employee.getEmployeeNumber(), periodStart, periodEnd);
        // get hourly rate
        double hourlyRate = rateService.computeHourlyRate(employee);
        // derive gross pay
        double grossPay = Math.round(totalHours * hourlyRate * 100.0) / 100.0;
        
        // get allowances
        double allowances = employee.getTotalAllowances();
        double totalDeductions = 0.0;
        double netPay = grossPay + allowances - totalDeductions;
        
        return new Payslip(
                employee.getEmployeeNumber(),
                employee.getFirstName() + " " + employee.getLastName(),
                employee.getPosition(),
                periodStart,
                periodEnd,
                totalHours,
                hourlyRate,
                grossPay,
                allowances,
                totalDeductions,
                netPay
        );
    }
}

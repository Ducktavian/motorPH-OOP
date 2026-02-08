/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.model;

import java.time.LocalDate;
/**
 *
 * @author Lenovo
 */
public class Payslip {
    
    // Employee Info
    private String employeeNumber;
    private String employeeName;
    private String position;
    
    // Payroll Period
    private LocalDate periodStart;
    private LocalDate periodEnd;
    
    // Earnings
    private double totalHours;
    private double hourlyRate;
    private double grossPay;
    private double allowances;
    
    // Deductions
    private double totalDeductions;
    
    // Final Pay
    private double netPay;
    
    public Payslip(String employeeNumber,
                   String employeeName,
                   String position,
                   LocalDate periodStart,
                   LocalDate periodEnd,
                   double totalHours,
                   double hourlyRate,
                   double grossPay,
                   double allowances,
                   double totalDeductions,
                   double netPay) {

        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.position = position;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.totalHours = totalHours;
        this.hourlyRate = hourlyRate;
        this.grossPay = grossPay;
        this.allowances = allowances;
        this.totalDeductions = totalDeductions;
        this.netPay = netPay;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPosition() {
        return position;
    }

    public LocalDate getPeriodStart() {
        return periodStart;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getAllowances() {
        return allowances;
    }

    public double getTotalDeductions() {
        return totalDeductions;
    }

    public double getNetPay() {
        return netPay;
    }
    
    
}

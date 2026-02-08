/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.service;

import com.motorph.model.Employee;

/**
 *
 * @author Lenovo
 */
public class RateService {
    
    private static final int WORKING_DAYS_PER_MONTH = 21;
    private static final int WORKING_HOURS_PER_DAY = 8;
    
    // Computes hourly rate based on basic salary
    
    public double computeHourlyRate(Employee employee) {
        
        // hourlyRate = basic salary / (21 * 8)
        double hourlyRate = employee.getBasicSalary() / (WORKING_DAYS_PER_MONTH * WORKING_HOURS_PER_DAY);
        
        return Math.round(hourlyRate * 100.0) / 100.0;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.service;

import com.motorph.dao.AttendanceDAO;
import com.motorph.model.AttendanceRecord;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class AttendanceService {
    
    private AttendanceDAO attendanceDAO;
    
    // Constructor
    public AttendanceService(AttendanceDAO attendanceDAO) {
        this.attendanceDAO = attendanceDAO;
    }
    
    // Computes hours worked for a single attendance record ( helper function)
    public double computeDailyHours(AttendanceRecord record) {
        Duration duration = Duration.between(
                record.getLogIn(),
                record.getLogOut()
        );
        
        double hours = duration.toMinutes() / 60.0;
        
        return Math.round(hours * 100.0) / 100.0; // 2 decimal places
    }
    
    // Computes total hours worked for an employee
    public double computeTotalHours(String employeeNumber) {
        List<AttendanceRecord> records = attendanceDAO.getAttendanceByEmployee(employeeNumber);
        
        double total = 0;
        
        for (AttendanceRecord record : records) {
            total += computeDailyHours(record);
        }
        
        return Math.round(total * 100.0) / 100.0;
    }
    
    // Period computation
    public double computeTotalHours(String employeeNumber, LocalDate periodStart, LocalDate periodEnd) {
        
        List<AttendanceRecord> records = attendanceDAO.getAttendanceByEmployee(employeeNumber);
        
        double total = 0;
        
        for (AttendanceRecord record: records) {
            
            LocalDate date = record.getDate();
            
            boolean withinPeriod = (date.isEqual(periodStart) || date.isAfter(periodStart)) &&
                                    (date.isEqual(periodEnd) || date.isBefore(periodEnd));
            
            if (withinPeriod) {
                total += computeDailyHours(record);
            }
        }
        
        return Math.round(total * 100.0) / 100.0;
    }
}

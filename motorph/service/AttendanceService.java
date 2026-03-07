
package com.motorph.service;

import com.motorph.dao.AttendanceDAO;
import com.motorph.dao.CsvAttendanceDAO;
import com.motorph.model.AttendanceRecord;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;


public class AttendanceService {
    
    private AttendanceDAO attendanceDAO = new CsvAttendanceDAO();
    
    // Constructor
    public AttendanceService() {
        
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
    
    // Get all atendances of an employee
    public List<AttendanceRecord> getAttendanceByEmployee(String employeeNumber) {
        return attendanceDAO.getAttendanceByEmployee(employeeNumber);
    }
}

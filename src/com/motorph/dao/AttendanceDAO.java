
package com.motorph.dao;

import com.motorph.model.AttendanceRecord;
import java.util.List;


public interface AttendanceDAO {
    
    List<AttendanceRecord> getAttendanceByEmployee(String employeeNumber);
    
    List<AttendanceRecord> getAllAttendance();
    
    AttendanceRecord getOpenSession(String employeeNumber);
    
    void timeIn(String employeeNumber, String lastName, String firstName);
    
    void timeOut(String employeeNumber);
}

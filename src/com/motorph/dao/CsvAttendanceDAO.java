
package com.motorph.dao;

import com.motorph.model.AttendanceRecord;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class CsvAttendanceDAO implements AttendanceDAO {
    
    private String filePath;
    
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("H:mm");
    
    public CsvAttendanceDAO(String filePath) {
        this.filePath = filePath;
    }
    
    

    @Override
    public List<AttendanceRecord> getAttendanceByEmployee(String employeeNumber) {
        List<AttendanceRecord> result = new ArrayList<>();
        
        for (AttendanceRecord record : getAllAttendance()) {
            if (record.getEmployeeNumber().equals(employeeNumber)) {
                result.add(record);
            }
        }
        
        return result;
    }

    @Override
    public List<AttendanceRecord> getAllAttendance() {
        List<AttendanceRecord> records = new ArrayList<>();
        
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            reader.readNext();
            
            while ((line = reader.readNext()) != null) {
                
                String employeeNumber = line[0];
                LocalDate date = LocalDate.parse(line[3], DATE_FORMAT);
                LocalTime logIn = LocalTime.parse(line[4], TIME_FORMAT);
                LocalTime logOut = LocalTime.parse(line[5], TIME_FORMAT);
                
                records.add(new AttendanceRecord(
                        employeeNumber,
                        date,
                        logIn,
                        logOut
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return records;
    }
    
}

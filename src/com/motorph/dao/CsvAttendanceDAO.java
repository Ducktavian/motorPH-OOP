
package com.motorph.dao;

import com.motorph.model.AttendanceRecord;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class CsvAttendanceDAO implements AttendanceDAO {
    
    private final String FILE_PATH = "data/attendance.csv";
    
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("H:mm");
    
    public CsvAttendanceDAO() {
        initializeFile();
    }
    
    
    private void initializeFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
                    // Typical attendance header based on your indices (0, 3, 4, 5)
                    String[] header = {"Employee #", "Last Name", "First Name", "Date", "Time-in", "Time-out"};
                    writer.writeNext(header);
                }
            }
        } catch (Exception e) {
            System.err.println("Could not initialize attendance file: " + e.getMessage());
        }
    }
    
    

    @Override
    // return all attendances of this employee
    public List<AttendanceRecord> getAttendanceByEmployee(String employeeNumber) {
        List<AttendanceRecord> result = new ArrayList<>();
        
        for (AttendanceRecord record : getAllAttendance()) {
            if (record.getEmployeeNumber().equals(employeeNumber.trim())) {
                result.add(record);
            }
        }
        
        return result;
    }

    @Override
    public List<AttendanceRecord> getAllAttendance() {
        List<AttendanceRecord> records = new ArrayList<>();
        
        File file = new File(FILE_PATH);
        
        if (!file.exists()) return records;
        
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            String[] line;
            reader.readNext(); // skip header
            
            while ((line = reader.readNext()) != null) {
                
                if (line.length >= 6) {
                    try {
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
                    } catch (Exception parseError) {
                        System.err.println("Skipping malformed attendance row: " + String.join(",", line));
                    }
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return records;
    }
    
}

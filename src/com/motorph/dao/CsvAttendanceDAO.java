
package com.motorph.dao;

import com.motorph.model.AttendanceRecord;
import com.motorph.util.DateUtils;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class CsvAttendanceDAO implements AttendanceDAO {
    
    private final String FILE_PATH = "data/attendance.csv";
    
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
                        LocalDate date = DateUtils.stringToDate(line[3]);
                        LocalTime logIn = DateUtils.stringToTime(line[4]);
                        LocalTime logOut = DateUtils.stringToTime(line[5]);

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
    
    @Override
    public AttendanceRecord getOpenSession(String employeeNumber) {
        LocalDate today = LocalDate.now();
        
        List<AttendanceRecord> records = getAllAttendance();
        
        for (int i = records.size() -1; i >= 0; i--) {
            AttendanceRecord r = records.get(i);
            
            if (r.getEmployeeNumber().equals(employeeNumber)
                    && r.getDate().equals(today)
                    && r.getLogOut() == null) {
                return r;            }
        }
        return null;
    }
    
    @Override
    public void timeIn(String employeeNumber, String lastName, String firstName) {
        
        // Checks if employee already timed in
        if (getOpenSession(employeeNumber) != null) {
            throw new IllegalStateException("Employee already timed in.");
        }
        
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
            
            String[] row = {
                employeeNumber,
                lastName,
                firstName,
                DateUtils.dateToString(today),
                DateUtils.timeToString(now),
                "" // Empty time out
            };
            
            writer.writeNext(row);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    @Override
    public void timeOut(String employeeNumber) {
        
        // Checks if theres an open session (has time-in but no time-out today in record)
        AttendanceRecord open = getOpenSession(employeeNumber);

        if (open == null) {
            throw new IllegalStateException("No active session found.");
        }

        List<String[]> allRows = new ArrayList<>();
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {

            String[] header = reader.readNext();
            allRows.add(header);

            String[] line;

            while ((line = reader.readNext()) != null) {

                if (line.length >= 6) {

                    String empNo = line[0];
                    LocalDate date = DateUtils.stringToDate(line[3]);
                    String timeOut = line[5];

                    // Match the open session
                    if (empNo.equals(employeeNumber)
                            && date.equals(today)
                            && (timeOut == null || timeOut.isEmpty())) {

                        line[5] = DateUtils.timeToString(now); // set timeout
                    }
                }

                allRows.add(line); // Add each line to the list
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Rewrite file
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
            writer.writeAll(allRows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
}

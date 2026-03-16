
package com.motorph.dao;

import com.motorph.model.AttendanceRecord;
import com.motorph.model.Employee;
import com.motorph.service.EmployeeService;
import com.motorph.util.AppContext;
import com.motorph.util.DateUtils;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CsvAttendanceDAO implements AttendanceDAO {
    
    private final String FILE_PATH = "data/attendance.csv";
    private List<AttendanceRecord> attendanceRecords = new ArrayList<>();;
    private EmployeeService empService;
    
    // Faster
    private Map<String, AttendanceRecord> activeSessions = new HashMap<>();
    
    public CsvAttendanceDAO() {
        initializeFile();
        loadAttendances();
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
    
    
    private void loadAttendances() {
        // Lazy loading
        if (this.empService == null) {
            this.empService = AppContext.getEmployeeService();
        }
        attendanceRecords.clear();   
        activeSessions.clear();
        
        File file = new File(FILE_PATH);        
        if (!file.exists()) return;
        
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            String[] line;
            reader.readNext(); // skip header
            LocalDate today = LocalDate.now();
            
            while ((line = reader.readNext()) != null) {
                if (line.length >= 6) {
                    AttendanceRecord record =new AttendanceRecord(
                        line[0],
                        line[1],
                        line[2],
                        DateUtils.stringToLocalDate(line[3]),
                        DateUtils.stringToTime(line[4]),
                        DateUtils.stringToTime(line[5])
                    );

                    attendanceRecords.add(record);

                    // If record is for today AND hasn't timed out, put it in the Map
                    if (record.getDate().equals(today) && record.getLogOut() == null) {
                        activeSessions.put(record.getEmployeeNumber(), record);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    
    
    public void saveAllAttendances() {
        
        if (this.empService == null) {
            this.empService = AppContext.getEmployeeService();
        }
        
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
            // Correct Attendance Header
            String[] header = {"Employee #", "Last Name", "First Name", "Date", "Time-in", "Time-out"};
            writer.writeNext(header);

            for (AttendanceRecord record : attendanceRecords) {
                String[] row = {
                    record.getEmployeeNumber(),
                    record.getLastName(),
                    record.getFirstName(),
                    DateUtils.dateToString(record.getDate()),
                    DateUtils.timeToString(record.getLogIn()),
                    DateUtils.timeToString(record.getLogOut())
                };
                writer.writeNext(row);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    
    
    @Override
    public void timeIn(String employeeNumber) {
        
        // Checks if employee already timed in
        if (getOpenSession(employeeNumber) != null) {
            throw new IllegalStateException("Employee already timed in.");
        }
        
        Employee emp = empService.findEmployee(employeeNumber);
        AttendanceRecord newRecord = new AttendanceRecord(
                employeeNumber, emp.getLastName(), emp.getFirstName(), LocalDate.now(), LocalTime.now(), null
        );
        
        attendanceRecords.add(newRecord);
        activeSessions.put(employeeNumber, newRecord);
        
        saveAllAttendances();
    }
    
    
    @Override
    public void timeOut(String employeeNumber) {
        
        // Checks if theres an open session (has time-in but no time-out today in record)
        AttendanceRecord open = getOpenSession(employeeNumber);

        if (open == null) {
            throw new IllegalStateException("No active session found.");
        }
        
        open.setLogOut(LocalTime.now()); // Set time out now
        activeSessions.remove(employeeNumber);
        saveAllAttendances();
    }
    
    
    @Override
    public List<AttendanceRecord> getAllAttendance() {
       return attendanceRecords;
    }
    
    @Override
    public AttendanceRecord getOpenSession(String employeeNumber) {
        return activeSessions.get(employeeNumber);
    }
}

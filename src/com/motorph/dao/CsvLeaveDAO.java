

package com.motorph.dao;

import com.motorph.model.LeaveRequest;
import com.motorph.model.LeaveType;
import com.motorph.model.RequestStatus;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class CsvLeaveDAO implements LeaveDAO {
    
    private static final String FILE_PATH = "data/leave_requests.csv";
    
    
    public CsvLeaveDAO() {
        
        // Initialize the file and directories as soon as DAO is created
        initializeFile();
    }
    
    private void initializeFile() {
           try {
            File file = new File(FILE_PATH);
            
            // Create folder if missing
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            
            // if file is new: write header
            if (!file.exists()) {
                try(CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
                    String[] header = {
                        "requestId","employeeNumber","dateFiled", "startDate",
                        "endDate","leaveType", "reason", "status","approvedBy"
                    };
                    writer.writeNext(header);
                }            
            }
        } catch (Exception e) {
            System.err.println("Could not initialize leave CSV: " + e.getMessage());
        }
    }
    
    
    @Override
    public void submitLeaveRequest(LeaveRequest leave) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH, true))) {
            writer.writeNext(mapLeaveRequestToRow(leave));            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<LeaveRequest> getAllRequests() {
        List<LeaveRequest> requests = new ArrayList<>();
        File file = new File(FILE_PATH);
        
        if (!file.exists()) return requests;
        
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            reader.readNext(); // Skip header
            
            String[] row;
            while ((row = reader.readNext()) != null) {
                // Skip empty or malformed rows
                if (row.length >= 8) {
                    requests.add(mapRowToLeaveRequest(row));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requests;
    }
    
    @Override
    public List<LeaveRequest> getRequestsByEmployee(String employeeNumber) {
        List<LeaveRequest> result = new ArrayList<>();
        for (LeaveRequest req : getAllRequests()) {
            if (req.getEmployeeNumber().equals(employeeNumber)) {
                result.add(req);
            }
        }
        return result;
    }
    
    @Override
    public LeaveRequest findById(String requestId) {
        for (LeaveRequest req : getAllRequests()) {
            if (req.getRequestId().equals(requestId)) {
                return req;
            }
        }
        
        return null;
    }
    
    
    @Override
    public void updateRequest(LeaveRequest updatedRequest) {
        
        List<LeaveRequest> requests = getAllRequests();
        
        // Write the header back
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
            String[] header = {"requestId", "employeeNumber", "dateFiled", "startDate", "endDate", "leaveType", "reason", "status", "approvedBy"};
            writer.writeNext(header);
            
            for (LeaveRequest req : requests) {
                if (req.getRequestId().equals(updatedRequest.getRequestId())) {
                    writer.writeNext(mapLeaveRequestToRow(updatedRequest));
                } else {
                    writer.writeNext(mapLeaveRequestToRow(req));
                }                
            }
        } catch (Exception e) {
            e.printStackTrace( );
        }
    }
    
    // Reconstruct a LeaveRequest object read from file
    private LeaveRequest mapRowToLeaveRequest(String[] row) {
        
        LeaveRequest request = new LeaveRequest(
                row[0],
                row[1],
                LocalDate.parse(row[2]),
                LocalDate.parse(row[3]),
                LocalDate.parse(row[4]),
                LeaveType.valueOf(row[5]),
                row[6]
        );
        
        request.setRequestStatus(RequestStatus.valueOf(row[7]));
        
        if (row.length > 8 && !row[8].isEmpty()) {
            request.setApprovedBy(row[8]);
        }                
        return request;
    }
    
    // Helper to keep logic clean and reusable
    private String[] mapLeaveRequestToRow(LeaveRequest leave) {
        return new String[] {
            leave.getRequestId(),
            leave.getEmployeeNumber(),
            leave.getDateFiled().toString(),
            leave.getStartDate().toString(),
            leave.getEndDate().toString(),
            leave.getLeaveType().name(),
            leave.getReason(),
            leave.getStatus().name(),
            leave.getApprovedBy() == null ? "" : leave.getApprovedBy()
        };
    }
}



package com.motorph.dao;

import com.motorph.model.LeaveRequest;
import com.motorph.model.LeaveType;
import com.motorph.model.RequestStatus;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class CsvLeaveDAO implements LeaveDAO {
    
    private static final String FILE_PATH = "data/leave_requests.csv";
    
    @Override
    public void submitLeaveRequest(LeaveRequest leave) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH, true))) {
            
            String[] row = {
                leave.getRequestId(),
                leave.getEmployeeId(),
                leave.getDateFiled().toString(),
                leave.getStartDate().toString(),
                leave.getEndDate().toString(),
                leave.getLeaveType().name(),
                leave.getReason(),
                leave.getStatus().name(),
                leave.getApprovedBy() == null ? "" :leave.getApprovedBy()
            };
            
            writer.writeNext(row);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<LeaveRequest> getAllRequests() {
        List<LeaveRequest> requests = new ArrayList<>();
        
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            
            String[] row;
            
            while ((row = reader.readNext()) != null) {
                
                LeaveRequest request = mapRowToLeaveRequest(row);
                requests.add(request);
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
            
            if (req.getEmployeeId().equals(employeeNumber)) {
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
        
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
            for (LeaveRequest req : requests) {
                if (req.getRequestId().equals(updatedRequest.getRequestId())) {
                    req = updatedRequest;
                }
                
                String[] row = {
                    req.getRequestId(),
                    req.getEmployeeId(),
                    req.getDateFiled().toString(),
                    req.getStartDate().toString(),
                    req.getEndDate().toString(),
                    req.getLeaveType().name(),
                    req.getReason(),
                    req.getStatus().name(),
                    req.getApprovedBy() == null ? "" : req.getApprovedBy()
                };

                writer.writeNext(row);
            }
        } catch (Exception e) {
            e.printStackTrace( );
        }
    }
    
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
        
        if (!row[8].isEmpty()) {
            request.setApprovedBy(row[8]);
        }
        
        
        return request;
    }
}

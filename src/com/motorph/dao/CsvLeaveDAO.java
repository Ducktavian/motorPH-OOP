

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
                leave.getLeaveType().toString(),
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

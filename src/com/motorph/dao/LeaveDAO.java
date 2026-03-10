
package com.motorph.dao;

import com.motorph.model.LeaveRequest;
import java.util.List;

public interface LeaveDAO {
    
    void submitLeaveRequest(LeaveRequest leave);
    
    List<LeaveRequest> getAllRequests();
    
    List<LeaveRequest> getRequestsByEmployee(String employeeNumber);
    
    LeaveRequest findById(String requestId);
    
    void updateRequest(LeaveRequest request);
    
}

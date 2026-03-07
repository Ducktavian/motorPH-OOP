package com.motorph.model;

import java.time.LocalDate;
/**
 *
 * @author Lenovo
 */
public abstract class Request implements Requestable {
    
    protected String requestId;
    protected String employeeId;
    protected LocalDate dateFiled;
    protected RequestStatus status;
    
    public Request(String requestId, String employeeId, LocalDate dateFiled) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.dateFiled = dateFiled;
        this.status = RequestStatus.PENDING;
    }
    
    public String getRequestId() {
        return requestId;
    }
    
    public String getEmployeeId() {
        return employeeId;
    }
    
    public LocalDate dateFiled() {
        return dateFiled;
    }
    
    public RequestStatus getStatus() {
        return status;
    }
    
    public void approve() {
        if (this.status == RequestStatus.PENDING) {
            this.status = RequestStatus.APPROVED;
        }
    }
    
    public void reject() {
        if (this.status == RequestStatus.PENDING) {
            this.status = RequestStatus.REJECTED;
        }
    }
    
    // Each request type computes impact differently
    public abstract double calculateImpact(double baseRate);
}

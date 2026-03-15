package com.motorph.model;

import java.time.LocalDate;
/**
 *
 * @author Lenovo
 */
public abstract class Request implements Requestable {
    
    protected String requestId;
    protected String employeeNumber;
    protected LocalDate dateFiled;
    protected RequestStatus status;
    protected String approvedBy;
    
    public Request(String requestId, String employeeId, LocalDate dateFiled) {
        this.requestId = requestId;
        this.employeeNumber = employeeId;
        this.dateFiled = dateFiled;
        this.status = RequestStatus.PENDING;
    }
    
    @Override
    public String getRequestId() {
        return requestId;
    }
    
    @Override
    public String getEmployeeNumber() {
        return employeeNumber;
    }
    
    @Override
    public LocalDate getDateFiled() {
        return dateFiled;
    }
    
    @Override
    public RequestStatus getStatus() {
        return status;
    }
    
    public String getApprovedBy() {
        return approvedBy;
    }
    
    public void setRequestStatus(RequestStatus status) {
        this.status = status;
    }
    
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;

    }
    
    @Override
    public void approve(String approverId) {
        if (this.status == RequestStatus.PENDING) {
            this.status = RequestStatus.APPROVED;
            this.approvedBy = approverId;

        }
    }
    
    @Override
    public void reject(String approverId) {
        if (this.status == RequestStatus.PENDING) {
            this.status = RequestStatus.DENIED;
            this.approvedBy = approverId;

        }
    }
    
    // Each request type computes impact differently
    public abstract double calculateImpact(double baseRate);
}

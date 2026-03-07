package com.motorph.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 *
 * @author Lenovo
 */
public class LeaveRequest extends Request {
    
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    
    public LeaveRequest(String requestId,
                        String employeeId,
                        LocalDate dateFiled,
                        LocalDate startDate,
                        LocalDate endDate,
                        String reason) {
        super(requestId, employeeId, dateFiled);
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
    
    public String getReason() {
        return reason;
    }
    
    // Calculates deduction amount (requires daily rate input)
    public double calculateImpact(double dailyRate) {
        long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return days * dailyRate;
    }
    
    
    public double calculateImpact() {
        return 0; // default
    }

}

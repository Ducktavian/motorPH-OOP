

package com.motorph.model;

import java.time.LocalDate;
/**
 *
 * @author Lenovo
 */
public class OvertimeRequest extends Request{
    
    private LocalDate overtimeDate;
    private double hours;
    
    public OvertimeRequest(String requestId,
                           String employeeId,
                           LocalDate dateFiled,
                           LocalDate overtimeDate,
                           double hours) {
        super(requestId, employeeId, dateFiled);
        this.overtimeDate = overtimeDate;
        this.hours = hours;
        
    }
    
    public LocalDate overtimeDate(){
        return overtimeDate;
    }
    public double getHours() {
        return hours;
    }
    
    @Override
    public double calculateImpact(double hourlyRate) {
        return hours * hourlyRate;
    }
}

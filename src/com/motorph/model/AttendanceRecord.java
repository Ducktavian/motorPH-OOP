package com.motorph.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceRecord {
    
    private String employeeNumber;
    private LocalDate date;
    private LocalTime logIn;
    private LocalTime logOut;

    public AttendanceRecord(String employeeNumber, LocalDate date, LocalTime logIn, LocalTime logOut) {
        this.employeeNumber = employeeNumber;
        this.date = date;
        this.logIn = logIn;
        this.logOut = logOut;
    }
    
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getLogIn() {
        return logIn;
    }

    public LocalTime getLogOut() {
        return logOut;
    }
    
    
    
}

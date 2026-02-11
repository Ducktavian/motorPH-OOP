package com.motorph.model;

import java.time.LocalDate;


public abstract class Employee {

    // Attributes must be PROTECTED
    protected String employeeNumber;
    protected String lastName;
    protected String firstName;
    protected String birthday;
    protected String address;
    protected String phoneNumber;
    
    protected String SSSNumber;
    protected String philhealthNumber;
    protected String TIN;
    protected String pagIbigNumber;
    
    protected String status;
    protected String position;
    protected String immediateSupervisor;

    protected double basicSalary;
    protected double riceSubsidy;
    protected double phoneAllowance;
    protected double clothingAllowance;
    
    // Empty parameters constructor
    public Employee () {
        
    }
    
    // Parameterized constructor
    public Employee(
            String employeeNumber,
            String lastName,
            String firstName,
            String birthday,
            String address,
            String phoneNumber,
            String SSSNumber,
            String philhealthNumber,
            String TIN,
            String pagIbigNumber,
            String status,
            String position,
            String immediateSupervisor,
            double basicSalary,
            double riceSubsidy,
            double phoneAllowance,
            double clothingAllowance
    ) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.SSSNumber = SSSNumber;
        this.philhealthNumber = philhealthNumber;
        this.TIN = TIN;
        this.pagIbigNumber = pagIbigNumber;
        this.status = status;
        this.position = position;
        this.immediateSupervisor = immediateSupervisor;
        this.basicSalary = basicSalary;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
    }

    // getters
    public String getEmployeeNumber() { return employeeNumber; }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPosition() {
        return position;
    }

    public double getBasicSalary() {
        return basicSalary;
    }
    
    
    public String getFullName() { 
        return firstName + " " + lastName; 
    }
    
    public double getHourlyRate() { 
        return basicSalary / (21 * 8);
    }
    
    public double getSemiMonthlyRate() {
        return basicSalary / 2;
    }
    
    public double getTotalAllowances() {
        return riceSubsidy + clothingAllowance + phoneAllowance;
    }
    
}



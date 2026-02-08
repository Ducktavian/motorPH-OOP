package com.motorph.model;

import java.time.LocalDate;


public abstract class Employee implements PayrollCalculation {

    // Attributes must be PROTECTED
    protected String employeeNumber;
    protected String lastName;
    protected String firstName;
    protected String position;

    protected double basicSalary;
    protected double riceSubsidy;
    protected double phoneAllowance;
    protected double clothingAllowance;
    protected double hourlyRate;
    
    // Empty parameters constructor
    public Employee () {
        
    }
    
    // Parameterized constructor
    public Employee(
            String employeeNumber,
            String lastName,
            String firstName,
            String position,
            double basicSalary,
            double riceSubsidy,
            double phoneAllowance,
            double clothingAllowance,
            double hourlyRate
    ) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.position = position;
        this.basicSalary = basicSalary;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.hourlyRate = hourlyRate;
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
    
    
    
    public String getFullName() { return firstName + " " + lastName; }
    public double getHourlyRate() { return hourlyRate; }
    
    public double getTotalAllowances() {
        return riceSubsidy + clothingAllowance + phoneAllowance;
    }
    
    
    
    // abstract methods
    @Override
    public abstract double computeGrossSalary();
    @Override
    public abstract double computeHourlyRate();
    
    
    

}



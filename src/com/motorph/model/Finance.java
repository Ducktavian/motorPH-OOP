package com.motorph.model;


public class Finance extends Employee {

    public Finance(String employeeNumber, String lastName, String firstName, String position, double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance, double hourlyRate) {
        super(employeeNumber, lastName, firstName, position, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance, hourlyRate);
    }
    
   
    @Override
    public double computeGrossSalary() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double computeHourlyRate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.model;

/**
 *
 * @author Lenovo
 */
public class IT extends Employee {

    public IT(String employeeNumber, String lastName, String firstName, String position, double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance, double hourlyRate) {
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

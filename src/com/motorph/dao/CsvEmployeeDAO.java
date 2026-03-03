
package com.motorph.dao;

import com.motorph.model.Employee;
import com.motorph.model.Finance;
import com.motorph.model.HR;
import com.motorph.model.IT;
import com.motorph.model.RegularEmployee;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import java.io.FileReader;

public class CsvEmployeeDAO implements EmployeeDAO {
    
    private String filePath = "employees.csv";
    private static final int POSITION_INDEX = 11;
    private List<Employee> employees;
    
    // Constructor
    public CsvEmployeeDAO() {
        employees = new ArrayList<>();
        loadEmployees();
        
    }
    
    
    @Override
    public List<Employee> getAllEmployees() {
        loadEmployees();
        
        return employees;
    }
    
    private void loadEmployees() {
        try (CSVReader reader = new CSVReader(new FileReader (filePath))) {
            
            String[] data;
            reader.readNext();
            
            while ((data = reader.readNext()) != null) {
                
                
                // Employee Creation
                String employeeNumber = data[0];
                String lastName = data[1];
                String firstName = data[2];
                String birthday = data[3];
                String address = data[4];
                String phoneNumber = data[5];
                String SSSNumber = data[6];
                String philhealthNumber = data[7];
                String TIN = data[8];
                String pagIbigNumber = data[9];
                String status = data[10];
                String position = data[POSITION_INDEX].toLowerCase();
                String immediateSupervisor = data[12];
                
                
                double basicSalary = Double.parseDouble(data[13].replace(",", ""));
                double riceSubsidy = Double.parseDouble(data[14].replace(",", ""));
                double phoneAllowance = Double.parseDouble(data[15].replace(",", ""));
                double clothingAllowance = Double.parseDouble(data[16].replace(",", ""));
               

                
                Employee employee;
                
                if (position.contains("hr")) {
                    employee = new HR(employeeNumber, lastName, firstName, birthday, address, phoneNumber, SSSNumber, philhealthNumber, TIN, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance);
                }
                else if (position.contains("it")) {
                    employee = new IT(employeeNumber, lastName, firstName, birthday, address, phoneNumber, SSSNumber, philhealthNumber, TIN, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance);
                }
                else if (position.contains("finance")
                    || position.contains("account")
                    || position.contains("payroll")) {
                    employee = new Finance(employeeNumber, lastName, firstName, birthday, address, phoneNumber, SSSNumber, philhealthNumber, TIN, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance);
                }
                else {
                    employee = new RegularEmployee(employeeNumber, lastName, firstName, birthday, address, phoneNumber, SSSNumber, philhealthNumber, TIN, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance);
                }
                
                employees.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // IDK
    private double parseAmount(String value) {
        if (value == null || value.equalsIgnoreCase("N/A") || value.isBlank()) {
            return 0.0;
        }
        return Double.parseDouble(value.replace(",", ""));
    }
    
    // Returns employee
    public Employee findEmployee(String employeeNumber) {
        String searchKey = employeeNumber.trim();
        
        for (Employee emp: employees) {
            if (emp.getEmployeeNumber().equals(searchKey)) {
                return emp;
            }
        }
       
        System.out.println("Could not find employee!");
        return null;
    }
    
    @Override
    public void addEmployee() {
        
    }

    @Override
    public void addEmployee(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateEmployee(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteEmployee(String employeeNumber) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

}

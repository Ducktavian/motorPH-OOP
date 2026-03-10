
package com.motorph.dao;

import com.motorph.model.Employee;
import com.motorph.model.Finance;
import com.motorph.model.HR;
import com.motorph.model.IT;
import com.motorph.model.RegularEmployee;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class CsvEmployeeDAO implements EmployeeDAO {
    
    private final String filePath = "employees.csv";
    private static final int POSITION_INDEX = 11;
    private List<Employee> employees;
    
    // Constructor
    public CsvEmployeeDAO() {
        employees = new ArrayList<>();
        loadEmployees();
        
    }
    
    
    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }
    
    private void loadEmployees() {
        
        employees.clear();
        
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
        return null;
    }
    
    @Override
    public void addEmployee(Employee employee) {
        
        employees.add(employee);
        
        saveAllEmployees();
    }

    @Override
    public void updateEmployee(Employee updatedEmployee) {
        
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeNumber().equals(updatedEmployee.getEmployeeNumber())) {
                 employees.set(i, updatedEmployee);
                break; 
            }
        }
        
        saveAllEmployees();
    }

    @Override
    public void deleteEmployee(String employeeNumber) {
        
        employees.removeIf(emp -> emp.getEmployeeNumber().equals(employeeNumber));
        
        saveAllEmployees();
    }

    // Writes in the file
    private void saveAllEmployees() {

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {

            String[] header = {
                "Employee #","Last Name","First Name","Birthday","Address",
                "Phone Number","SSS #","Philhealth #","TIN","Pag-ibig #",
                "Status","Position","Immediate Supervisor",
                "Basic Salary","Rice Subsidy","Phone Allowance","Clothing Allowance",
                "Gross Semi-monthly Rate", "Hourly Rate"
                
            };

            writer.writeNext(header);

            for (Employee emp : employees) {

                String[] row = {
                    emp.getEmployeeNumber(),
                    emp.getLastName(),
                    emp.getFirstName(),
                    emp.getBirthday(),
                    emp.getAddress(),
                    emp.getPhoneNumber(),
                    emp.getSSSNumber(),
                    emp.getPhilhealthNumber(),
                    emp.getTIN(),
                    emp.getPagIbigNumber(),
                    emp.getStatus(),
                    emp.getPosition(),
                    emp.getImmediateSupervisor(),
                    String.valueOf(emp.getBasicSalary()),
                    String.valueOf(emp.getRiceSubsidy()),
                    String.valueOf(emp.getPhoneAllowance()),
                    String.valueOf(emp.getClothingAllowance()),
                    String.valueOf(emp.getSemiMonthlyRate()),
                    String.valueOf(emp.getHourlyRate())
                        
                };

                writer.writeNext(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public String generateNextEmployeeNumber() {
        int max = 0;
        
        for (Employee emp: employees) {
            
            int current = Integer.parseInt(emp.getEmployeeNumber());
            
            if (current > max) {
                max = current;
            }
        }
        
        int next = max + 1;
        
        return String.valueOf(next);
    }
    

}

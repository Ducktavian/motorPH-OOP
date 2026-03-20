
package com.motorph.dao;

import com.motorph.model.Employee;
import com.motorph.model.Finance;
import com.motorph.model.HR;
import com.motorph.model.IT;
import com.motorph.model.RegularEmployee;
import com.motorph.util.DateUtils;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

public class CsvEmployeeDAO implements EmployeeDAO {
    
    private final String FILE_PATH = "data/employees.csv";
    private static final int POSITION_INDEX = 11;
    private List<Employee> employees;
    
    // Constructor
    public CsvEmployeeDAO() {
        this.employees = new ArrayList<>();
        ensureFileExists();
        loadEmployees();
    }
    
    private void ensureFileExists() {
        try {
            File file = new File(FILE_PATH);
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                saveAllEmployees();
            }
        } catch (Exception e) {
            System.err.println("Initial file creation failed: " + e.getMessage());
        }
    }
    
    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }
    
    private void loadEmployees() {
        employees.clear();        
        File file = new File(FILE_PATH);        
        if (!file.exists()) return;
        
        try (CSVReader reader = new CSVReader(new FileReader (FILE_PATH))) {
            
            String[] data;
            reader.readNext(); // skips header
            
            while ((data = reader.readNext()) != null) {
                
                if (data.length < 17) continue; // skips malformed rows
                
                employees.add(createEmployeeInstance(data));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
  
    
    // Helper to manage employee
    public Employee createEmployeeInstance(String[] data) {
        // Employee Creation
        String employeeNumber = data[0];
        String lastName = data[1];
        String firstName = data[2];
        LocalDate birthday = DateUtils.stringToLocalDate(data[3]);
        String address = data[4];
        String phoneNumber = data[5];
        String SSSNumber = data[6];
        String philhealthNumber = data[7];
        String TIN = data[8];
        String pagIbigNumber = data[9];
        String status = data[10];
        String position = toTitleCase(data[POSITION_INDEX]);
        String immediateSupervisor = data[12];

        double basicSalary = parseAmount(data[13]);
        double riceSubsidy = parseAmount(data[14]);
        double phoneAllowance = parseAmount(data[15]);
        double clothingAllowance = parseAmount(data[16]);

        Employee employee;
        
        String _position = position.toLowerCase();
        if (_position.contains("hr")) {
            employee = new HR(employeeNumber, lastName, firstName, birthday, address, phoneNumber, SSSNumber, philhealthNumber, TIN, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance);
        }
        else if (_position.contains("it")) {
            employee = new IT(employeeNumber, lastName, firstName, birthday, address, phoneNumber, SSSNumber, philhealthNumber, TIN, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance);
        }
        else if (_position.contains("finance")
            || _position.contains("account")
            || _position.contains("payroll")) {
            employee = new Finance(employeeNumber, lastName, firstName, birthday, address, phoneNumber, SSSNumber, philhealthNumber, TIN, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance);
        }
        else {
            employee = new RegularEmployee(employeeNumber, lastName, firstName, birthday, address, phoneNumber, SSSNumber, philhealthNumber, TIN, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance);
        }
        
        return employee;
    }
    
    
    
    public static String toTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
                titleCase.append(c);
            } else if (nextTitleCase) {
                // Character.toTitleCase handles Unicode title case rules
                titleCase.append(Character.toTitleCase(c));
                nextTitleCase = false;
            } else {
                // Convert subsequent characters to lowercase for standard title case
                titleCase.append(Character.toLowerCase(c));
            }
        }
        return titleCase.toString();
    }
    
    
    // helper to pasrse doubles
    private double parseAmount(String value) {
        if (value == null || value.equalsIgnoreCase("N/A") || value.isBlank()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value.replace(",", ""));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    // Returns employee
    @Override
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
        validateUniqueIds(employee);
        employees.add(employee);
        saveAllEmployees();
    }

    @Override
    public void updateEmployee(Employee updatedEmployee) {
        validateUniqueIds(updatedEmployee);
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeNumber().equals(updatedEmployee.getEmployeeNumber())) {
                employees.set(i, updatedEmployee);
                saveAllEmployees();
                return;
            }
        }
    }

    @Override
    public void deleteEmployee(String employeeNumber) {
        if (employees.removeIf(emp -> emp.getEmployeeNumber().equals(employeeNumber))) {
            saveAllEmployees();
        }
        
    }

    // Writes in the file
    private void saveAllEmployees() {
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
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
                    emp.getEmployeeNumber(),emp.getLastName(),emp.getFirstName(),
                    DateUtils.dateToString(emp.getBirthday()),emp.getAddress(),emp.getPhoneNumber(),
                    formatSSS(emp.getSSSNumber()),emp.getPhilhealthNumber(),formatTIN(emp.getTIN()),
                    emp.getPagIbigNumber(),emp.getStatus(),emp.getPosition(),
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
    
    // Use to auto-generate the employee number of a new employee
    public String generateNextEmployeeNumber() {
        if (employees.isEmpty()) {
            return "10001"; 
        }
        
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
    
    // helper formatters
    private String formatSSS(String sss) {
        if (sss == null || sss.length() != 10) return sss;
        return sss.substring(0, 2) + "-" + sss.substring(2, 9) + "-" + sss.substring(9);
    }
    
    private String formatTIN(String tin) {
        if (tin == null || tin.length() != 12) return tin;
        return tin.substring(0, 3) + "-" + tin.substring(3, 6) + "-" + 
           tin.substring(6, 9) + "-" + tin.substring(9, 11) + tin.substring(11);
    }
    
    private void validateUniqueIds(Employee employee) throws IllegalArgumentException {
    for (Employee existing : employees) {
        // Skip the check if it's the same employee (important for update)
        if (existing.getEmployeeNumber().equals(employee.getEmployeeNumber())) {
            continue;
        }

        if (existing.getSSSNumber().replaceAll("[^0-9]", "").equals(employee.getSSSNumber().replaceAll("[^0-9]", ""))) {
            throw new IllegalArgumentException("SSS Number " + employee.getSSSNumber() + " already exists.");
        }
        if (existing.getPhilhealthNumber().equals(employee.getPhilhealthNumber())) {
            throw new IllegalArgumentException("PhilHealth Number " + employee.getPhilhealthNumber() + " already exists.");
        }
        if (existing.getTIN().replaceAll("[^0-9]", "").equals(employee.getTIN().replaceAll("[^0-9]", ""))) {
            throw new IllegalArgumentException("TIN " + employee.getTIN() + " already exists.");
        }
        if (existing.getPagIbigNumber().equals(employee.getPagIbigNumber())) {
            throw new IllegalArgumentException("Pag-Ibig Number " + employee.getPagIbigNumber() + " already exists.");
        }
    }
}
}

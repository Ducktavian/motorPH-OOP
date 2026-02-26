package com.motorph.dao;

import com.motorph.model.Employee;
import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAllEmployees();
    
    // Wrtie employee
    void addEmployee();
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(String employeeNumber);
}

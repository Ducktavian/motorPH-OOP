package com.motorph.dao;

import com.motorph.model.Employee;
import java.util.List;

public interface EmployeeDAO {

    List<Employee> getAllEmployees();
    
    // Wrtie employee
    void addEmployee();
}

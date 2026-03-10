

package com.motorph.service;

import com.motorph.dao.EmployeeDAO;
import com.motorph.model.Employee;
import java.util.List;

public class EmployeeService {

    private EmployeeDAO employeeDAO;
    
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }
    
    public Employee findEmployee(String employeeNumber) {
        return employeeDAO.findEmployee(employeeNumber);
    }
    
    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }
    
    public void updateEmployee(Employee employee ){
        employeeDAO.updateEmployee(employee);
    }
    
    public void deleteEmployee(String employeeNumber) {
        employeeDAO.deleteEmployee(employeeNumber);
    }
    
    public String generateNextEmployeeNumber() {
        return employeeDAO.generateNextEmployeeNumber();
    }
}


package com.motorph.main;

import com.motorph.dao.AttendanceDAO;
import com.motorph.dao.CsvAttendanceDAO;
import com.motorph.dao.CsvEmployeeDAO;
import com.motorph.dao.EmployeeDAO;
import com.motorph.model.AttendanceRecord;
import com.motorph.model.Employee;
import com.motorph.model.Payslip;
import com.motorph.model.RegularEmployee;
import com.motorph.model.Role;
import com.motorph.model.UserAccount;
import com.motorph.service.AttendanceService;
import com.motorph.service.AuthService;
import com.motorph.service.DeductionService;
import com.motorph.service.EmployeeService;
import com.motorph.service.PayrollService;
import com.motorph.service.RateService;
import com.motorph.ui.EmployeeDashboardUI;
import com.motorph.ui.EmployeeLeaveUI;
import com.motorph.ui.LoginUI;
import com.motorph.util.Session;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import  com.motorph.ui.*;

//

public class Main {
    
    public static void main(String[] args) {
        // Use invokeLater to ensure thread safety for Swing components
        java.awt.EventQueue.invokeLater(() -> {
            LoginUI loginFrame = new LoginUI();
            loginFrame.setLocationRelativeTo(null); // Centers the window on screen
            loginFrame.setVisible(true);
            
                    
        });
        
        // TESTING
        EmployeeDAO empDao = new CsvEmployeeDAO();
        EmployeeService empService = new EmployeeService(empDao);
        
        
        testingAddEmployee(empService);
        //testingDeleteEmployee(empService, "10035");
        
        
    }
    
    private static void testingAddEmployee(EmployeeService empService) {
        String employeeNumber = empService.generateNextEmployeeNumber();
        String lastName = "Human";
        String firstName = "Human";
        LocalDate birthday = LocalDate.parse("3/30/1930");
        String address = "Korea";
        String phoneNumber = "09";
        String SSSNumber = "4234324";
        String philhealthNumber = "676767";
        String TIN = "676767";
        String pagIbigNumber = "6767767";
        String status = "Regualar";
        String position = "Manager";
        String immediateSupervisor = "Manuel";

        double basicSalary = 67000.00;
        double riceSubsidy = 670;
        double phoneAllowance = 670;
        double clothingAllowance = 670;
        
        Employee employee = new RegularEmployee(employeeNumber, lastName, firstName, birthday, address, phoneNumber, SSSNumber, philhealthNumber, TIN, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance);
        
        empService.addEmployee(employee);
    }
    
    private static void testingDeleteEmployee(EmployeeService empService, String employeeNumber) {
        empService.deleteEmployee(employeeNumber);
    }
}
        
     
    
   
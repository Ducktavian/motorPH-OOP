/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.main;

import com.motorph.dao.AttendanceDAO;
import com.motorph.dao.CsvAttendanceDAO;
import com.motorph.dao.CsvEmployeeDAO;
import com.motorph.dao.EmployeeDAO;
import com.motorph.model.AttendanceRecord;
import com.motorph.model.Employee;
import com.motorph.model.Payslip;
import com.motorph.service.AttendanceService;
import com.motorph.service.PayrollService;
import com.motorph.service.RateService;
import com.motorph.ui.DashboardFrame;
import java.time.LocalDate;
import java.util.List;


//

public class Main {
    public static void main(String[] args) {
        
        /*
        // 1. Point to your CSV file
        EmployeeDAO dao = new CsvEmployeeDAO("employees.csv");

        // 2. Load employees
        for (Employee e : dao.getAllEmployees()) {

            // 3. Print basic info
            System.out.println(e.getFullName());
            System.out.println("Class: " + e.getClass().getSimpleName());
            System.out.println("Gross Salary: " + e.computeGrossSalary());
            System.out.println("Hourly Rate: " + e.computeHourlyRate());
            System.out.println("------------------------");
        }
        */
        
        // Creates a csv attendance dao object
        AttendanceDAO attendanceDAO = new CsvAttendanceDAO("attendance.csv");
        
        // Create attendance service object
        AttendanceService attendanceService =
            new AttendanceService(attendanceDAO);
        
        
        // Create RateService object
        RateService rateService = new RateService();
        
        // Create PayrollService object
        PayrollService payrollService = new PayrollService(attendanceService, rateService);
        
        // Create employee dao object
        EmployeeDAO employeeDAO = new CsvEmployeeDAO("employees.csv");
        
        // Create employee object by getting the first employee from the return value of dao
        Employee employee = employeeDAO.getAllEmployees().get(0);
        
        
        // Payslip object
        Payslip payslip = payrollService.generatePayslip(
            employee,
            LocalDate.of(2024, 6, 1),
            LocalDate.of(2024, 6, 15)
        );
                
        System.out.println("Payslip Generated:");
        System.out.println("Employee: " + payslip.getEmployeeName());
        System.out.println("Hours Worked: " + payslip.getTotalHours());
        System.out.println("Gross Pay: " + payslip.getGrossPay());
        System.out.println("Allowances: " + payslip.getAllowances());
        System.out.println("Net Pay: " + payslip.getNetPay());
        
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            new DashboardFrame().setVisible(true);
        });
        
    }  
}
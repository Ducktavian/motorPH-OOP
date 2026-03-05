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
import com.motorph.model.UserAccount;
import com.motorph.service.AttendanceService;
import com.motorph.service.AuthService;
import com.motorph.service.PayrollService;
import com.motorph.service.RateService;
import com.motorph.ui.LoginFrame;
import com.motorph.util.Session;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

//

public class Main {
    
    private static CsvEmployeeDAO csvEmpDao = new CsvEmployeeDAO();
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        AuthService authService = new AuthService();
        UserAccount user;
        boolean running = true;
        boolean isLoggedIn = false;


        while (running) {
           // Create scanner
           
           // Login loop
           while (!isLoggedIn) {
                System.out.println("Login");
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                System.out.print("Enter Password: ");
                String password = scanner.nextLine();

                try  {
                    user = authService.login(username, password);
                    System.out.println("Welcome " + user.getUsername() + "!");
                    
                    // Set session
                    Session.setCurrentUser(user);
                    
                    System.out.println(user.getEmployeeNumber());
                    System.out.println(user.getPasswordHash());
                    System.out.println(user.getRole());
                    System.out.println(user.isActive() + "");
                  
                    isLoggedIn = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
           }

                
           
           String menu = "\nSelect Action:\n1-View Employee Details\n2-View Attendance Log\n3-View Payroll Details\n4-File a Request\n5-Log out";
           System.out.println(menu);
           
           
           
           int selectedAction = scanner.nextInt();
           scanner.nextLine();
           
           switch (selectedAction) {
               case 1:
                   viewEmployeeDetails();
                   break;
               case 2:
                   viewAttendanceLog(Session.getCurrentUser().getEmployeeNumber());
                   break;
               case 3:
                   viewPayrollDetails();
                   break;
               case 4:
                   fileRequest();
                   break;
               case 5:
                   break;
               default:
                   System.out.println("Invalid Choice!");
           }
       }
       scanner.close();
    }  
    
    public static void viewEmployeeDetails() {
       
        Employee employee = csvEmpDao.findEmployee(Session.getCurrentUser().getEmployeeNumber());
        
        String stringFormat = "%-23s %s%n";
        String doubleFormat = "%-23s %,.2f%n";
        
        System.out.println("\n---Employee Details:");
        System.out.printf(stringFormat, "Employee Number:", employee.getEmployeeNumber());
        System.out.printf(stringFormat, "Full Name:", employee.getFullName());
        System.out.printf(stringFormat, "Birthday:", employee.getBirthday());
        System.out.printf(stringFormat, "Address:", employee.getAddress());
        System.out.printf(stringFormat, "Phone Number:", employee.getPhoneNumber());
        System.out.printf(doubleFormat, "Basic Salary:", employee.getBasicSalary());
        System.out.printf(doubleFormat, "Daily Rate:", employee.getDailyRate());
        System.out.printf(stringFormat, "Position:", employee.getPosition());
        System.out.printf(stringFormat, "Immediate Supervisor:", employee.getImmediateSupervisor());
        System.out.printf(stringFormat, "SSS Number:", employee.getSSSNumber());
        System.out.printf(stringFormat, "Philhealth Number:", employee.getPhilhealthNumber());
        System.out.printf(stringFormat, "TIN:", employee.getTIN());
        System.out.printf(stringFormat, "Pag-IBIG Number:", employee.getPagIbigNumber());
        System.out.printf(stringFormat, "Status:", employee.getStatus());
        
    }
    
    public static void viewAttendanceLog(String employeeNumber) {
        List<AttendanceRecord> attendanceRecord;
        AttendanceService attendanceService = new AttendanceService();
        
        attendanceRecord = attendanceService.getAttendanceByEmployee(employeeNumber);
        
        System.out.println("\n---Attendance Record:");
        for (AttendanceRecord atRecord: attendanceRecord) {
            System.out.print(atRecord.getDate() + " | ");
            System.out.print(atRecord.getLogIn()+ " - ");
            System.out.println(atRecord.getLogOut() + "");
        }
    }
    
    public static void viewPayrollDetails() {
    
    }
    
    public static void fileRequest() {}
}
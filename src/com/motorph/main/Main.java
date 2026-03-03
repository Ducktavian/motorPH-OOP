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
import com.motorph.ui.LoginFrame;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

//

public class Main {
    public static void main(String[] args) {
        
       boolean running = true;
       
       while (running) {
           System.out.println("Log-In as:\n1-Employee\n2-HR\n3-Finace\n4-IT\n5-Exit");
           Scanner scanner = new Scanner(System.in);
           
           
           System.out.println("Login");
           System.out.print("Enter username: ");
           String username = scanner.nextLine();
           
           System.out.print("Enter Password: ");
           String password = scanner.nextLine();
           
           int logInChoice = scanner.nextInt();
           
           switch (logInChoice) {
               case 1:
                   
               case 2:
               case 3:
               case 4:
                   
               case 5:
                   break;
           }
       }
        
    }  
}
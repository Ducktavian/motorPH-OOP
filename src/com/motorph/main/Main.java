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
import com.motorph.model.Role;
import com.motorph.model.UserAccount;
import com.motorph.service.AttendanceService;
import com.motorph.service.AuthService;
import com.motorph.service.DeductionService;
import com.motorph.service.PayrollService;
import com.motorph.service.RateService;
import com.motorph.ui.EmployeeDashboardFrame;
import com.motorph.ui.EmployeeLeaveFrame;
import com.motorph.ui.LoginFrame;
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
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setLocationRelativeTo(null); // Centers the window on screen
            loginFrame.setVisible(true);
            
                    
        });
    }
}
        
     
    
   
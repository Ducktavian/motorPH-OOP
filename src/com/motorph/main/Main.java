
package com.motorph.main;

import com.motorph.dao.AttendanceDAO;
import com.motorph.dao.CsvAttendanceDAO;
import com.motorph.dao.CsvEmployeeDAO;
import com.motorph.dao.CsvPayslipDAO;
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
        
        int action;
        
        while (true) {
            
        }
        
    }

    private void timeIn() {
        
    }
    
    private void timeOut() {
        
    }
    
    
    
}
        
     
    
   
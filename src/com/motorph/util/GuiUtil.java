package com.motorph.util;

import com.motorph.ui.MainAdminDashboardUI;
import com.motorph.ui.MainEmployeeDashboardUI;
import com.motorph.ui.MainFinanceDashboardUI;
import com.motorph.ui.MainHRDashboardUI;
import com.motorph.ui.MainITDashboardUI;
import javax.swing.JTextField;


public class GuiUtil {
    public static double getDoubleFromField(JTextField field) {
        String text = field.getText().trim();
        return (text.isEmpty()) ? 0.0 : Double.parseDouble(text.replace(",", ""));
    }
    
    public static void openFrame(javax.swing.JFrame oldFrame, javax.swing.JFrame newFrame) {
        oldFrame.dispose();
        newFrame.setVisible(true);
    }
    
    public static void openFrame(javax.swing.JFrame oldFrame) {
        String role = Session.getCurrentUser().getRole().name().trim();
            
            switch (role) {
                case "ADMIN":
                    openFrame(oldFrame, new MainAdminDashboardUI());
                    break;
                case "IT":
                    openFrame(oldFrame, new MainITDashboardUI());
                    break;
                case "FINANCE":
                    openFrame(oldFrame, new MainFinanceDashboardUI());
                    break;
                case "HR":
                    openFrame(oldFrame, new MainHRDashboardUI());
                    break;
                default: // Employee
                    openFrame(oldFrame, new MainEmployeeDashboardUI());
                    break;
            }
    }
}

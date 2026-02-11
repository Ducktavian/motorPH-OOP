/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.util;

import com.motorph.dao.AttendanceDAO;
import com.motorph.dao.CsvAttendanceDAO;
import com.motorph.dao.CsvEmployeeDAO;
import com.motorph.dao.EmployeeDAO;
import com.motorph.service.AttendanceService;
import com.motorph.service.PayrollService;
import com.motorph.service.RateService;

/**
 *
 * @author Lenovo
 */
public class AppContext {

    private static PayrollService payrollService;
    private static EmployeeDAO employeeDAO;

    static {
        AttendanceDAO attendanceDAO = new CsvAttendanceDAO("attendance.csv");
        AttendanceService attendanceService = new AttendanceService(attendanceDAO);
        RateService rateService = new RateService();

        payrollService = new PayrollService(attendanceService, rateService);
        employeeDAO = new CsvEmployeeDAO("employees.csv");
    }

    public static PayrollService getPayrollService() {
        return payrollService;
    }

    public static EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }
}


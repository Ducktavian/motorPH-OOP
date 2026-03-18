/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.motorph.ui;

import com.motorph.model.Employee;
import com.motorph.model.PayrollPeriod;
import com.motorph.model.Payslip;
import com.motorph.service.AttendanceService;
import com.motorph.service.EmployeeService;
import com.motorph.service.PayrollService;
import com.motorph.util.AppContext;
import com.motorph.util.GuiUtil;
import java.time.LocalDate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class FinanceGeneratePayrollUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FinanceGeneratePayrollUI.class.getName());
    private EmployeeService empService;
    private PayrollService payrollService;
    private AttendanceService attendanceService;
    
   
    public FinanceGeneratePayrollUI() {
        this.empService = AppContext.getEmployeeService();
        this.payrollService = AppContext.getPayrollService();
        this.attendanceService = AppContext.getAttendanceService();
        initComponents();
        initComboBox();
    }
    
    // Initializes leave type dropdown
    private void initComboBox() {
        PayrollPeriod[] periods = PayrollPeriod.values();
        DefaultComboBoxModel<Object> model = new DefaultComboBoxModel<>();
        model.addElement("Select Payroll Period");
        for (PayrollPeriod period : periods) {
            model.addElement(period);
        }
        financeGPrlPrlPeriodCbx.setModel(model);
    }
    

    
    private void populateEmployeeFields(Employee emp) {
        financeGPrlENameFld.setText(emp.getFullName());
        financeGPrlENumberFld.setText(emp.getEmployeeNumber());
        
    }
    
    private void performSearch() {
        String empNum = employeePrlRecordEntENumberFld.getText().trim();
        Employee emp = empService.findEmployee(empNum);
        
        if (emp == null) {
            clearFields();
            
        } else {
            populateEmployeeFields(emp);
            
        }
        
    }
    
    private void clearFields() {
        financeGPrlENameFld.setText("");
        financeGPrlENumberFld.setText("");
    }
    
    private Payslip generatePayslip(Employee emp) {
        
        Object selectedItem = financeGPrlPrlPeriodCbx.getSelectedItem();
        if (!(selectedItem instanceof PayrollPeriod)) {
            throw new IllegalArgumentException("Please select a valid payroll period.");
        }
        
        try {
            int month = financeGPrlMChsr.getMonth() + 1; // 0 indexed
            int year = financeGPrlYChsr.getYear();
            PayrollPeriod selectedPeriod = (PayrollPeriod) financeGPrlPrlPeriodCbx.getSelectedItem();
            
            LocalDate start = selectedPeriod.getStartDate(year, month);
            LocalDate end = selectedPeriod.getEndDate(year, month);
            
            if (start.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Cannot generate payroll for a future date");
            }
            
            double hours = attendanceService.computeTotalHours(emp.getEmployeeNumber(), start, end);
            if (hours <= 0) {
                throw new IllegalArgumentException("No attendance records found for this period.");
            }
                        
            return payrollService.generatePayslip(emp, start, end);
                  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error generating payslip: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    private void populateSalaryCalculations(Payslip payslip) {
        
        Employee emp = empService.findEmployee(payslip.getEmployeeNumber());
        financeGPrlBasicSalaryFld.setText(String.valueOf(emp.getBasicSalary()));
        financeGPrlOvertimeFld.setText("");
        financeGPrlHrsWorkedFld.setText(String.valueOf(payslip.getTotalHours()));
        financeGPrlHourlyRateFld.setText(String.valueOf(emp.getHourlyRate()));
        financeGPrlRiceSubsidyFld.setText(String.valueOf(payslip.getAllowanceBreakdown().getRiceSubsidy()));
        financeGPrlPhnAllowanceFld.setText(String.valueOf(payslip.getAllowanceBreakdown().getPhoneAllowance()));
        financeGPrlCltAllowanceFld.setText(String.valueOf(payslip.getAllowanceBreakdown().getClothingAllowance()));

        financeGPrlTGrossFld.setText(String.valueOf(payslip.getGrossPay()));



        financeGPrlSSSFld.setText(String.valueOf(payslip.getDeductionBreakdown().getSss()));
        financeGPrlPagIbigFld.setText(String.valueOf(payslip.getDeductionBreakdown().getPhilHealth()));
        financeGPrlPhilHealthFld.setText(String.valueOf(payslip.getDeductionBreakdown().getPagIbig()));
        financeGPrlWithtaxFld.setText(String.valueOf(payslip.getDeductionBreakdown().getWithholdingTax()));
        financeGPrlUndertimeFld.setText("");
        financeGPrlTDeductionFld.setText(String.valueOf(payslip.getDeductionBreakdown().getTotal()));

        financeGPrlNetPayFld.setText(String.valueOf(payslip.getNetPay()));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        financeGPrlENumberFld = new javax.swing.JTextField();
        financeGPrlENameLbl = new javax.swing.JLabel();
        financeGPrlPrlDateLbl = new javax.swing.JLabel();
        financeGPrlENameFld = new javax.swing.JTextField();
        financeGPrlPrlPeriodLbl = new javax.swing.JLabel();
        financeGPrlGPrlPnl = new javax.swing.JPanel();
        financeGPrlGPrlLbl = new javax.swing.JLabel();
        financeGPrlSidebarBtn = new javax.swing.JPanel();
        financeGPrlMainDashboardBtn = new javax.swing.JButton();
        financeGPrlMotorPHIconImgLbl = new javax.swing.JLabel();
        financeGPrlPrlDetailsBtn = new javax.swing.JButton();
        financeGPrlGPrlBtn = new javax.swing.JButton();
        financeGPrlPrlDListBtn = new javax.swing.JButton();
        financeGPrlGenerateBtn = new javax.swing.JButton();
        financeGPrlENumberLbl = new javax.swing.JLabel();
        financeGPrlUploadBtn = new javax.swing.JButton();
        financeGPrlPrlPeriodCbx = new javax.swing.JComboBox<>();
        financeGPrlSCalculatorBrdrPnl = new javax.swing.JPanel();
        financeGPrlSCalculatorLbl = new javax.swing.JLabel();
        financeGPrlTGrossBrdrPnl = new javax.swing.JPanel();
        financeGPrlEarningLbl = new javax.swing.JLabel();
        financeGPrlBasicSalaryLbl = new javax.swing.JLabel();
        financeGPrlOvertimeLbl = new javax.swing.JLabel();
        financeGPrlHrsWorkedLbl = new javax.swing.JLabel();
        financeGPrlBenefitLbl = new javax.swing.JLabel();
        financeGPrlRiceSubsidyLbl = new javax.swing.JLabel();
        financeGPrlPhnAllowanceLbl = new javax.swing.JLabel();
        financeGPrlCltAllowanceLbl = new javax.swing.JLabel();
        financeGPrlBasicSalaryFld = new javax.swing.JTextField();
        financeGPrlOvertimeFld = new javax.swing.JTextField();
        financeGPrlHrsWorkedFld = new javax.swing.JTextField();
        financeGPrlRiceSubsidyFld = new javax.swing.JTextField();
        financeGPrlPhnAllowanceFld = new javax.swing.JTextField();
        financeGPrlCltAllowanceFld = new javax.swing.JTextField();
        financeGPrlTGrossFld = new javax.swing.JTextField();
        financeGPrlTGrossLbl = new javax.swing.JLabel();
        financeGPrlHourlyRateLbl = new javax.swing.JLabel();
        financeGPrlHourlyRateFld = new javax.swing.JTextField();
        financeGPrlTDeductionBrdrPnl = new javax.swing.JPanel();
        financeGPrlDeductionLbl = new javax.swing.JLabel();
        financeGPrlSSSLbl = new javax.swing.JLabel();
        financeGPrlWithTaxLbl = new javax.swing.JLabel();
        financeGPrlPhilHealthLbl = new javax.swing.JLabel();
        financeGPrlUndertimeLbl = new javax.swing.JLabel();
        financeGPrlSSSFld = new javax.swing.JTextField();
        financeGPrlWithtaxFld = new javax.swing.JTextField();
        financeGPrlPhilHealthFld = new javax.swing.JTextField();
        financeGPrlUndertimeFld = new javax.swing.JTextField();
        financeGPrlTDeductionFld = new javax.swing.JTextField();
        financeGPrlTDeductionLbl = new javax.swing.JLabel();
        financeGPrlPagIbigFld = new javax.swing.JTextField();
        financeGPrlPagIbigLbl = new javax.swing.JLabel();
        financeGPrlNetPayLbl = new javax.swing.JLabel();
        financeGPrlNetPayFld = new javax.swing.JTextField();
        financeGPrlMChsr = new com.toedter.calendar.JMonthChooser();
        financeGPrlYChsr = new com.toedter.calendar.JYearChooser();
        employeePrlRecordSearchPnl = new javax.swing.JPanel();
        employeePrlRecordEntENumberFld = new javax.swing.JTextField();
        employeePrlRecordSearchIconImgLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        financeGPrlENumberFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlENumberFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlENumberFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlENumberFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlENumberFld.setEnabled(false);
        financeGPrlENumberFld.addActionListener(this::financeGPrlENumberFldActionPerformed);

        financeGPrlENameLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlENameLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlENameLbl.setText("Employee Name");

        financeGPrlPrlDateLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlPrlDateLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlPrlDateLbl.setText("Payroll Date");

        financeGPrlENameFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlENameFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlENameFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlENameFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlENameFld.setEnabled(false);
        financeGPrlENameFld.addActionListener(this::financeGPrlENameFldActionPerformed);

        financeGPrlPrlPeriodLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlPrlPeriodLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlPrlPeriodLbl.setText("Payroll Period");

        financeGPrlGPrlPnl.setBackground(new java.awt.Color(30, 58, 138));
        financeGPrlGPrlPnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlGPrlPnl.setForeground(new java.awt.Color(30, 58, 138));

        financeGPrlGPrlLbl.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        financeGPrlGPrlLbl.setForeground(new java.awt.Color(255, 255, 255));
        financeGPrlGPrlLbl.setText("Generate Payroll");

        javax.swing.GroupLayout financeGPrlGPrlPnlLayout = new javax.swing.GroupLayout(financeGPrlGPrlPnl);
        financeGPrlGPrlPnl.setLayout(financeGPrlGPrlPnlLayout);
        financeGPrlGPrlPnlLayout.setHorizontalGroup(
            financeGPrlGPrlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financeGPrlGPrlPnlLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(financeGPrlGPrlLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        financeGPrlGPrlPnlLayout.setVerticalGroup(
            financeGPrlGPrlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(financeGPrlGPrlLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        financeGPrlGPrlLbl.getAccessibleContext().setAccessibleName("financePrlGPrlLbl");

        financeGPrlSidebarBtn.setBackground(new java.awt.Color(30, 58, 138));
        financeGPrlSidebarBtn.setPreferredSize(new java.awt.Dimension(262, 700));

        financeGPrlMainDashboardBtn.setBackground(new java.awt.Color(30, 42, 56));
        financeGPrlMainDashboardBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGPrlMainDashboardBtn.setForeground(new java.awt.Color(255, 255, 255));
        financeGPrlMainDashboardBtn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        financeGPrlMainDashboardBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        financeGPrlMainDashboardBtn.setLabel("Main Dashboard");
        financeGPrlMainDashboardBtn.addActionListener(this::financeGPrlMainDashboardBtnActionPerformed);

        financeGPrlMotorPHIconImgLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorph/img/MotorPHIconImg.png"))); // NOI18N

        financeGPrlPrlDetailsBtn.setBackground(new java.awt.Color(30, 42, 56));
        financeGPrlPrlDetailsBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGPrlPrlDetailsBtn.setForeground(new java.awt.Color(255, 255, 255));
        financeGPrlPrlDetailsBtn.setText("Payroll Records");
        financeGPrlPrlDetailsBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        financeGPrlPrlDetailsBtn.addActionListener(this::financeGPrlPrlDetailsBtnActionPerformed);

        financeGPrlGPrlBtn.setBackground(new java.awt.Color(30, 42, 56));
        financeGPrlGPrlBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGPrlGPrlBtn.setForeground(new java.awt.Color(255, 255, 255));
        financeGPrlGPrlBtn.setText("Generate Payroll");
        financeGPrlGPrlBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        financeGPrlGPrlBtn.addActionListener(this::financeGPrlGPrlBtnActionPerformed);

        financeGPrlPrlDListBtn.setBackground(new java.awt.Color(30, 42, 56));
        financeGPrlPrlDListBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGPrlPrlDListBtn.setForeground(new java.awt.Color(255, 255, 255));
        financeGPrlPrlDListBtn.setText("Payroll Dispute List");
        financeGPrlPrlDListBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        financeGPrlPrlDListBtn.addActionListener(this::financeGPrlPrlDListBtnActionPerformed);

        javax.swing.GroupLayout financeGPrlSidebarBtnLayout = new javax.swing.GroupLayout(financeGPrlSidebarBtn);
        financeGPrlSidebarBtn.setLayout(financeGPrlSidebarBtnLayout);
        financeGPrlSidebarBtnLayout.setHorizontalGroup(
            financeGPrlSidebarBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financeGPrlSidebarBtnLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(financeGPrlMainDashboardBtn))
            .addGroup(financeGPrlSidebarBtnLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(financeGPrlSidebarBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(financeGPrlPrlDListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGPrlGPrlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(financeGPrlSidebarBtnLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(financeGPrlMotorPHIconImgLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(financeGPrlPrlDetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        financeGPrlSidebarBtnLayout.setVerticalGroup(
            financeGPrlSidebarBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financeGPrlSidebarBtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(financeGPrlSidebarBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(financeGPrlMotorPHIconImgLbl)
                    .addGroup(financeGPrlSidebarBtnLayout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(financeGPrlPrlDetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(financeGPrlGPrlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(financeGPrlPrlDListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(307, 307, 307)
                .addComponent(financeGPrlMainDashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        financeGPrlMainDashboardBtn.getAccessibleContext().setAccessibleName("financePrlMainDashboardBtn");
        financeGPrlMotorPHIconImgLbl.getAccessibleContext().setAccessibleName("financePrlMotorPHIconImgLbl");
        financeGPrlPrlDetailsBtn.getAccessibleContext().setAccessibleName("financePrlPrlRecordDetailsBtn");
        financeGPrlGPrlBtn.getAccessibleContext().setAccessibleName("financePrlGPrlBtn");
        financeGPrlPrlDListBtn.getAccessibleContext().setAccessibleName("financeGPrlPrlDListBtn");

        financeGPrlGenerateBtn.setBackground(new java.awt.Color(30, 58, 138));
        financeGPrlGenerateBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGPrlGenerateBtn.setForeground(new java.awt.Color(255, 255, 255));
        financeGPrlGenerateBtn.setText("Generate");
        financeGPrlGenerateBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlGenerateBtn.addActionListener(this::financeGPrlGenerateBtnActionPerformed);

        financeGPrlENumberLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlENumberLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlENumberLbl.setText("Employee #");

        financeGPrlUploadBtn.setBackground(new java.awt.Color(34, 197, 94));
        financeGPrlUploadBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGPrlUploadBtn.setForeground(new java.awt.Color(255, 255, 255));
        financeGPrlUploadBtn.setText("Upload");
        financeGPrlUploadBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlUploadBtn.addActionListener(this::financeGPrlUploadBtnActionPerformed);

        financeGPrlPrlPeriodCbx.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlPrlPeriodCbx.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(31, 41, 55), 1, true));

        financeGPrlSCalculatorBrdrPnl.setBackground(new java.awt.Color(146, 192, 253));
        financeGPrlSCalculatorBrdrPnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlSCalculatorBrdrPnl.setForeground(new java.awt.Color(146, 192, 253));
        financeGPrlSCalculatorBrdrPnl.setPreferredSize(new java.awt.Dimension(710, 408));

        financeGPrlSCalculatorLbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGPrlSCalculatorLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlSCalculatorLbl.setText("Salary Calculator");

        financeGPrlTGrossBrdrPnl.setBackground(new java.awt.Color(233, 233, 233));
        financeGPrlTGrossBrdrPnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlTGrossBrdrPnl.setForeground(new java.awt.Color(30, 58, 138));

        financeGPrlEarningLbl.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        financeGPrlEarningLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlEarningLbl.setText("Earning");

        financeGPrlBasicSalaryLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlBasicSalaryLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlBasicSalaryLbl.setText("Basic Salary");

        financeGPrlOvertimeLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlOvertimeLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlOvertimeLbl.setText("Overtime");

        financeGPrlHrsWorkedLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlHrsWorkedLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlHrsWorkedLbl.setText("Hour/s Worked");

        financeGPrlBenefitLbl.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        financeGPrlBenefitLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlBenefitLbl.setText("Benefit");

        financeGPrlRiceSubsidyLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlRiceSubsidyLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlRiceSubsidyLbl.setText("Rice Subsidy");

        financeGPrlPhnAllowanceLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlPhnAllowanceLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlPhnAllowanceLbl.setText("Phone Allowance");

        financeGPrlCltAllowanceLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlCltAllowanceLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlCltAllowanceLbl.setText("Clothing Allowance");

        financeGPrlBasicSalaryFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlBasicSalaryFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlBasicSalaryFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlBasicSalaryFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlBasicSalaryFld.setEnabled(false);
        financeGPrlBasicSalaryFld.addActionListener(this::financeGPrlBasicSalaryFldActionPerformed);

        financeGPrlOvertimeFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlOvertimeFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlOvertimeFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlOvertimeFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlOvertimeFld.setEnabled(false);
        financeGPrlOvertimeFld.addActionListener(this::financeGPrlOvertimeFldActionPerformed);

        financeGPrlHrsWorkedFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlHrsWorkedFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlHrsWorkedFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlHrsWorkedFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlHrsWorkedFld.setEnabled(false);
        financeGPrlHrsWorkedFld.addActionListener(this::financeGPrlHrsWorkedFldActionPerformed);

        financeGPrlRiceSubsidyFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlRiceSubsidyFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlRiceSubsidyFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlRiceSubsidyFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlRiceSubsidyFld.setEnabled(false);
        financeGPrlRiceSubsidyFld.addActionListener(this::financeGPrlRiceSubsidyFldActionPerformed);

        financeGPrlPhnAllowanceFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlPhnAllowanceFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlPhnAllowanceFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlPhnAllowanceFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlPhnAllowanceFld.setEnabled(false);
        financeGPrlPhnAllowanceFld.addActionListener(this::financeGPrlPhnAllowanceFldActionPerformed);

        financeGPrlCltAllowanceFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlCltAllowanceFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlCltAllowanceFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlCltAllowanceFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlCltAllowanceFld.setEnabled(false);
        financeGPrlCltAllowanceFld.addActionListener(this::financeGPrlCltAllowanceFldActionPerformed);

        financeGPrlTGrossFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlTGrossFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlTGrossFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlTGrossFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlTGrossFld.setEnabled(false);
        financeGPrlTGrossFld.addActionListener(this::financeGPrlTGrossFldActionPerformed);

        financeGPrlTGrossLbl.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        financeGPrlTGrossLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlTGrossLbl.setText("Total Gross");

        financeGPrlHourlyRateLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlHourlyRateLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlHourlyRateLbl.setText("Hourly Rate");

        financeGPrlHourlyRateFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlHourlyRateFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlHourlyRateFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlHourlyRateFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlHourlyRateFld.setEnabled(false);
        financeGPrlHourlyRateFld.addActionListener(this::financeGPrlHourlyRateFldActionPerformed);

        javax.swing.GroupLayout financeGPrlTGrossBrdrPnlLayout = new javax.swing.GroupLayout(financeGPrlTGrossBrdrPnl);
        financeGPrlTGrossBrdrPnl.setLayout(financeGPrlTGrossBrdrPnlLayout);
        financeGPrlTGrossBrdrPnlLayout.setHorizontalGroup(
            financeGPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGPrlTGrossBrdrPnlLayout.createSequentialGroup()
                .addGroup(financeGPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, financeGPrlTGrossBrdrPnlLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addGroup(financeGPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(financeGPrlEarningLbl)
                            .addComponent(financeGPrlBenefitLbl))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, financeGPrlTGrossBrdrPnlLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(financeGPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(financeGPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGPrlHourlyRateLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGPrlHourlyRateFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGPrlOvertimeLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGPrlOvertimeFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGPrlBasicSalaryLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGPrlBasicSalaryFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGPrlHrsWorkedLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(financeGPrlHrsWorkedFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGPrlRiceSubsidyLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGPrlRiceSubsidyFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(financeGPrlTGrossLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(financeGPrlTGrossFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGPrlCltAllowanceLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGPrlCltAllowanceFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGPrlPhnAllowanceLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGPrlPhnAllowanceFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(26, 26, 26))
        );
        financeGPrlTGrossBrdrPnlLayout.setVerticalGroup(
            financeGPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financeGPrlTGrossBrdrPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(financeGPrlEarningLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlBasicSalaryLbl)
                    .addComponent(financeGPrlBasicSalaryFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlOvertimeLbl)
                    .addComponent(financeGPrlOvertimeFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlHrsWorkedLbl)
                    .addComponent(financeGPrlHrsWorkedFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlHourlyRateLbl)
                    .addComponent(financeGPrlHourlyRateFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(financeGPrlBenefitLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(financeGPrlRiceSubsidyFld, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGPrlRiceSubsidyLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlPhnAllowanceFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGPrlPhnAllowanceLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlCltAllowanceFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGPrlCltAllowanceLbl))
                .addGap(28, 28, 28)
                .addGroup(financeGPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlTGrossLbl)
                    .addComponent(financeGPrlTGrossFld, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        financeGPrlTDeductionBrdrPnl.setBackground(new java.awt.Color(233, 233, 233));
        financeGPrlTDeductionBrdrPnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlTDeductionBrdrPnl.setForeground(new java.awt.Color(30, 58, 138));

        financeGPrlDeductionLbl.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        financeGPrlDeductionLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlDeductionLbl.setText("Deduction");

        financeGPrlSSSLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlSSSLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlSSSLbl.setText("SSS");

        financeGPrlWithTaxLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlWithTaxLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlWithTaxLbl.setText("Withholding Tax");

        financeGPrlPhilHealthLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlPhilHealthLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlPhilHealthLbl.setText("PhilHealth");

        financeGPrlUndertimeLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlUndertimeLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlUndertimeLbl.setText("Undertime");

        financeGPrlSSSFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlSSSFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlSSSFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlSSSFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlSSSFld.setEnabled(false);
        financeGPrlSSSFld.addActionListener(this::financeGPrlSSSFldActionPerformed);

        financeGPrlWithtaxFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlWithtaxFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlWithtaxFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlWithtaxFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlWithtaxFld.setEnabled(false);
        financeGPrlWithtaxFld.addActionListener(this::financeGPrlWithtaxFldActionPerformed);

        financeGPrlPhilHealthFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlPhilHealthFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlPhilHealthFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlPhilHealthFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlPhilHealthFld.setEnabled(false);
        financeGPrlPhilHealthFld.addActionListener(this::financeGPrlPhilHealthFldActionPerformed);

        financeGPrlUndertimeFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlUndertimeFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlUndertimeFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlUndertimeFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlUndertimeFld.setEnabled(false);
        financeGPrlUndertimeFld.addActionListener(this::financeGPrlUndertimeFldActionPerformed);

        financeGPrlTDeductionFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlTDeductionFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlTDeductionFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlTDeductionFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlTDeductionFld.setEnabled(false);
        financeGPrlTDeductionFld.addActionListener(this::financeGPrlTDeductionFldActionPerformed);

        financeGPrlTDeductionLbl.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        financeGPrlTDeductionLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlTDeductionLbl.setText("Total Deduction");

        financeGPrlPagIbigFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlPagIbigFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlPagIbigFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGPrlPagIbigFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGPrlPagIbigFld.setEnabled(false);
        financeGPrlPagIbigFld.addActionListener(this::financeGPrlPagIbigFldActionPerformed);

        financeGPrlPagIbigLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGPrlPagIbigLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlPagIbigLbl.setText("Pag-Ibig");

        javax.swing.GroupLayout financeGPrlTDeductionBrdrPnlLayout = new javax.swing.GroupLayout(financeGPrlTDeductionBrdrPnl);
        financeGPrlTDeductionBrdrPnl.setLayout(financeGPrlTDeductionBrdrPnlLayout);
        financeGPrlTDeductionBrdrPnlLayout.setHorizontalGroup(
            financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(financeGPrlDeductionLbl)
                        .addGap(0, 97, Short.MAX_VALUE))
                    .addGroup(financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(financeGPrlTDeductionLbl)
                        .addGap(18, 18, 18)
                        .addComponent(financeGPrlTDeductionFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGPrlPagIbigLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGPrlPagIbigFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGPrlUndertimeLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGPrlUndertimeFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGPrlSSSLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGPrlSSSFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGPrlPhilHealthLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGPrlPhilHealthFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGPrlWithTaxLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGPrlWithtaxFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        financeGPrlTDeductionBrdrPnlLayout.setVerticalGroup(
            financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(financeGPrlDeductionLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlSSSLbl)
                    .addComponent(financeGPrlSSSFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlPagIbigFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGPrlPagIbigLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlPhilHealthLbl)
                    .addComponent(financeGPrlPhilHealthFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlWithTaxLbl)
                    .addComponent(financeGPrlWithtaxFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlUndertimeLbl)
                    .addComponent(financeGPrlUndertimeFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlTDeductionFld, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGPrlTDeductionLbl))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        financeGPrlNetPayLbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGPrlNetPayLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGPrlNetPayLbl.setText("Net Pay");

        financeGPrlNetPayFld.setBackground(new java.awt.Color(34, 197, 94));
        financeGPrlNetPayFld.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        financeGPrlNetPayFld.setForeground(new java.awt.Color(255, 255, 255));
        financeGPrlNetPayFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlNetPayFld.setCaretColor(new java.awt.Color(255, 255, 255));
        financeGPrlNetPayFld.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        financeGPrlNetPayFld.setEnabled(false);
        financeGPrlNetPayFld.addActionListener(this::financeGPrlNetPayFldActionPerformed);

        javax.swing.GroupLayout financeGPrlSCalculatorBrdrPnlLayout = new javax.swing.GroupLayout(financeGPrlSCalculatorBrdrPnl);
        financeGPrlSCalculatorBrdrPnl.setLayout(financeGPrlSCalculatorBrdrPnlLayout);
        financeGPrlSCalculatorBrdrPnlLayout.setHorizontalGroup(
            financeGPrlSCalculatorBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financeGPrlSCalculatorBrdrPnlLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(financeGPrlTGrossBrdrPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(financeGPrlSCalculatorBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(financeGPrlTDeductionBrdrPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGPrlSCalculatorBrdrPnlLayout.createSequentialGroup()
                        .addComponent(financeGPrlNetPayLbl)
                        .addGap(32, 32, 32)
                        .addComponent(financeGPrlNetPayFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGPrlSCalculatorBrdrPnlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(financeGPrlSCalculatorLbl)
                .addGap(283, 283, 283))
        );
        financeGPrlSCalculatorBrdrPnlLayout.setVerticalGroup(
            financeGPrlSCalculatorBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financeGPrlSCalculatorBrdrPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(financeGPrlSCalculatorLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlSCalculatorBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(financeGPrlSCalculatorBrdrPnlLayout.createSequentialGroup()
                        .addComponent(financeGPrlTDeductionBrdrPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(financeGPrlSCalculatorBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(financeGPrlNetPayFld, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(financeGPrlNetPayLbl)))
                    .addComponent(financeGPrlTGrossBrdrPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        financeGPrlSCalculatorLbl.getAccessibleContext().setAccessibleName("financeGPrlSCalculatorLbl");

        financeGPrlMChsr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));

        financeGPrlYChsr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));

        employeePrlRecordSearchPnl.setBackground(new java.awt.Color(146, 192, 253));
        employeePrlRecordSearchPnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        employeePrlRecordSearchPnl.setForeground(new java.awt.Color(146, 192, 253));
        employeePrlRecordSearchPnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        employeePrlRecordEntENumberFld.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        employeePrlRecordEntENumberFld.setForeground(new java.awt.Color(31, 41, 55));
        employeePrlRecordEntENumberFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        employeePrlRecordEntENumberFld.setCaretColor(new java.awt.Color(31, 41, 55));
        employeePrlRecordEntENumberFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        employeePrlRecordEntENumberFld.addActionListener(this::employeePrlRecordEntENumberFldActionPerformed);
        employeePrlRecordSearchPnl.add(employeePrlRecordEntENumberFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 170, 31));

        employeePrlRecordSearchIconImgLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorph/img/SearchIconImg.png"))); // NOI18N
        employeePrlRecordSearchPnl.add(employeePrlRecordSearchIconImgLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, -10, 60, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(financeGPrlSidebarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(financeGPrlGPrlPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(employeePrlRecordSearchPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(financeGPrlSCalculatorBrdrPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(financeGPrlGenerateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(financeGPrlUploadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(financeGPrlENameLbl)
                                .addGap(18, 18, 18)
                                .addComponent(financeGPrlENameFld, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(financeGPrlENumberLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGPrlENumberFld, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(financeGPrlPrlPeriodLbl)
                            .addComponent(financeGPrlPrlDateLbl))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(financeGPrlMChsr, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(financeGPrlYChsr, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(financeGPrlPrlPeriodCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(financeGPrlGPrlPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeePrlRecordSearchPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(financeGPrlPrlDateLbl)
                            .addGap(15, 15, 15)
                            .addComponent(financeGPrlPrlPeriodLbl))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(financeGPrlMChsr, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(financeGPrlYChsr, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(9, 9, 9)
                            .addComponent(financeGPrlPrlPeriodCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(financeGPrlENumberFld, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(financeGPrlENumberLbl))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(financeGPrlENameFld, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(financeGPrlENameLbl))))
                .addGap(18, 18, 18)
                .addComponent(financeGPrlSCalculatorBrdrPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGPrlGenerateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGPrlUploadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(financeGPrlSidebarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        financeGPrlENumberFld.getAccessibleContext().setAccessibleName("financeGPrlENumberFld");
        financeGPrlENameLbl.getAccessibleContext().setAccessibleName("financePrlENameLbl");
        financeGPrlPrlDateLbl.getAccessibleContext().setAccessibleName("financePrlPrlDateLbl");
        financeGPrlENameFld.getAccessibleContext().setAccessibleName("financePrlENameFld");
        financeGPrlPrlPeriodLbl.getAccessibleContext().setAccessibleName("financePrlPrlPeriodLbl");
        financeGPrlGPrlPnl.getAccessibleContext().setAccessibleName("financePrlGPrlPnl");
        financeGPrlSidebarBtn.getAccessibleContext().setAccessibleName("financePrlSidebarBtn");
        financeGPrlGenerateBtn.getAccessibleContext().setAccessibleName("financeGPrlGenerateBtn");
        financeGPrlENumberLbl.getAccessibleContext().setAccessibleName("financePrlENumberLbl");
        financeGPrlUploadBtn.getAccessibleContext().setAccessibleName("financeGPrlUploadBtn");
        financeGPrlPrlPeriodCbx.getAccessibleContext().setAccessibleName("financePrlPrlPeriodCbx");
        financeGPrlSCalculatorBrdrPnl.getAccessibleContext().setAccessibleName("financeGPrlSCalculatorBrdrPnl");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void financeGPrlENumberFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlENumberFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlENumberFldActionPerformed

    private void financeGPrlENameFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlENameFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlENameFldActionPerformed

    private void financeGPrlMainDashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlMainDashboardBtnActionPerformed
        // TODO add your handling code here:
        GuiUtil.openFrame(this);
    }//GEN-LAST:event_financeGPrlMainDashboardBtnActionPerformed

    private void financeGPrlPrlDetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlPrlDetailsBtnActionPerformed
        // TODO add your handling code here:
        GuiUtil.openFrame(this, new FinancePayrollRecordsUI());
    }//GEN-LAST:event_financeGPrlPrlDetailsBtnActionPerformed

    private void financeGPrlGenerateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlGenerateBtnActionPerformed
        // TODO add your handling code here:
        try {
            String employeeNumber = financeGPrlENumberFld.getText().trim();
            if (employeeNumber == null || employeeNumber.isBlank() || employeeNumber.isEmpty()) {
                throw new IllegalArgumentException("Search for an Employee first.");
            }
            
            
            Employee emp = empService.findEmployee(employeeNumber);
            Payslip payslip = generatePayslip(emp);
            if (payslip == null) {
                throw new IllegalArgumentException("Failed to generate payslip");
            }
            populateSalaryCalculations(payslip);
            
            JOptionPane.showMessageDialog(this, "Payslip Generated Successfully!");
            
            
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage());
        }
       
    }//GEN-LAST:event_financeGPrlGenerateBtnActionPerformed

    private void financeGPrlUploadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlUploadBtnActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_financeGPrlUploadBtnActionPerformed

    private void financeGPrlBasicSalaryFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlBasicSalaryFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlBasicSalaryFldActionPerformed

    private void financeGPrlOvertimeFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlOvertimeFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlOvertimeFldActionPerformed

    private void financeGPrlHrsWorkedFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlHrsWorkedFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlHrsWorkedFldActionPerformed

    private void financeGPrlRiceSubsidyFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlRiceSubsidyFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlRiceSubsidyFldActionPerformed

    private void financeGPrlPhnAllowanceFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlPhnAllowanceFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlPhnAllowanceFldActionPerformed

    private void financeGPrlCltAllowanceFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlCltAllowanceFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlCltAllowanceFldActionPerformed

    private void financeGPrlTGrossFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlTGrossFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlTGrossFldActionPerformed

    private void financeGPrlHourlyRateFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlHourlyRateFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlHourlyRateFldActionPerformed

    private void financeGPrlSSSFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlSSSFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlSSSFldActionPerformed

    private void financeGPrlWithtaxFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlWithtaxFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlWithtaxFldActionPerformed

    private void financeGPrlPhilHealthFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlPhilHealthFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlPhilHealthFldActionPerformed

    private void financeGPrlUndertimeFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlUndertimeFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlUndertimeFldActionPerformed

    private void financeGPrlTDeductionFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlTDeductionFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlTDeductionFldActionPerformed

    private void financeGPrlPagIbigFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlPagIbigFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlPagIbigFldActionPerformed

    private void financeGPrlNetPayFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlNetPayFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlNetPayFldActionPerformed

    private void financeGPrlGPrlBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlGPrlBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGPrlGPrlBtnActionPerformed

    private void financeGPrlPrlDListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGPrlPrlDListBtnActionPerformed
        // TODO add your handling code here:
        GuiUtil.openFrame(this, new FinancePayrollDisputeList());
    }//GEN-LAST:event_financeGPrlPrlDListBtnActionPerformed

    private void employeePrlRecordEntENumberFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeePrlRecordEntENumberFldActionPerformed
        // TODO add your handling code here:
        performSearch();
    }//GEN-LAST:event_employeePrlRecordEntENumberFldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FinanceGeneratePayrollUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField employeePrlRecordEntENumberFld;
    private javax.swing.JLabel employeePrlRecordSearchIconImgLbl;
    private javax.swing.JPanel employeePrlRecordSearchPnl;
    private javax.swing.JTextField financeGPrlBasicSalaryFld;
    private javax.swing.JLabel financeGPrlBasicSalaryLbl;
    private javax.swing.JLabel financeGPrlBenefitLbl;
    private javax.swing.JTextField financeGPrlCltAllowanceFld;
    private javax.swing.JLabel financeGPrlCltAllowanceLbl;
    private javax.swing.JLabel financeGPrlDeductionLbl;
    private javax.swing.JTextField financeGPrlENameFld;
    private javax.swing.JLabel financeGPrlENameLbl;
    private javax.swing.JTextField financeGPrlENumberFld;
    private javax.swing.JLabel financeGPrlENumberLbl;
    private javax.swing.JLabel financeGPrlEarningLbl;
    private javax.swing.JButton financeGPrlGPrlBtn;
    private javax.swing.JLabel financeGPrlGPrlLbl;
    private javax.swing.JPanel financeGPrlGPrlPnl;
    private javax.swing.JButton financeGPrlGenerateBtn;
    private javax.swing.JTextField financeGPrlHourlyRateFld;
    private javax.swing.JLabel financeGPrlHourlyRateLbl;
    private javax.swing.JTextField financeGPrlHrsWorkedFld;
    private javax.swing.JLabel financeGPrlHrsWorkedLbl;
    private com.toedter.calendar.JMonthChooser financeGPrlMChsr;
    private javax.swing.JButton financeGPrlMainDashboardBtn;
    private javax.swing.JLabel financeGPrlMotorPHIconImgLbl;
    private javax.swing.JTextField financeGPrlNetPayFld;
    private javax.swing.JLabel financeGPrlNetPayLbl;
    private javax.swing.JTextField financeGPrlOvertimeFld;
    private javax.swing.JLabel financeGPrlOvertimeLbl;
    private javax.swing.JTextField financeGPrlPagIbigFld;
    private javax.swing.JLabel financeGPrlPagIbigLbl;
    private javax.swing.JTextField financeGPrlPhilHealthFld;
    private javax.swing.JLabel financeGPrlPhilHealthLbl;
    private javax.swing.JTextField financeGPrlPhnAllowanceFld;
    private javax.swing.JLabel financeGPrlPhnAllowanceLbl;
    private javax.swing.JButton financeGPrlPrlDListBtn;
    private javax.swing.JLabel financeGPrlPrlDateLbl;
    private javax.swing.JButton financeGPrlPrlDetailsBtn;
    private javax.swing.JComboBox<Object> financeGPrlPrlPeriodCbx;
    private javax.swing.JLabel financeGPrlPrlPeriodLbl;
    private javax.swing.JTextField financeGPrlRiceSubsidyFld;
    private javax.swing.JLabel financeGPrlRiceSubsidyLbl;
    private javax.swing.JPanel financeGPrlSCalculatorBrdrPnl;
    private javax.swing.JLabel financeGPrlSCalculatorLbl;
    private javax.swing.JTextField financeGPrlSSSFld;
    private javax.swing.JLabel financeGPrlSSSLbl;
    private javax.swing.JPanel financeGPrlSidebarBtn;
    private javax.swing.JPanel financeGPrlTDeductionBrdrPnl;
    private javax.swing.JTextField financeGPrlTDeductionFld;
    private javax.swing.JLabel financeGPrlTDeductionLbl;
    private javax.swing.JPanel financeGPrlTGrossBrdrPnl;
    private javax.swing.JTextField financeGPrlTGrossFld;
    private javax.swing.JLabel financeGPrlTGrossLbl;
    private javax.swing.JTextField financeGPrlUndertimeFld;
    private javax.swing.JLabel financeGPrlUndertimeLbl;
    private javax.swing.JButton financeGPrlUploadBtn;
    private javax.swing.JLabel financeGPrlWithTaxLbl;
    private javax.swing.JTextField financeGPrlWithtaxFld;
    private com.toedter.calendar.JYearChooser financeGPrlYChsr;
    // End of variables declaration//GEN-END:variables
}

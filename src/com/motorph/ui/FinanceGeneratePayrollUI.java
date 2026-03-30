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
    private Payslip payslip;
    
   
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
        financeGenPrlPrlPeriodCbx.setModel(model);
    }
    

    
    private void populateEmployeeFields(Employee emp) {
        financeGenPrlENameFld.setText(emp.getFullName());
        financeGenPrlENumberFld.setText(emp.getEmployeeNumber());
        
    }
    
    private void performSearch() {
        String empNum = financeGenPrlEntENumberFld.getText().trim();
        Employee emp = empService.findEmployee(empNum);
        
        if (emp == null) {
            clearFields();
            
        } else {
            populateEmployeeFields(emp);
            
        }
        
    }
    
    private void clearFields() {
        financeGenPrlENameFld.setText("");
        financeGenPrlENumberFld.setText("");
    }
    
    private Payslip generatePayslip(Employee emp) {
        
        Object selectedItem = financeGenPrlPrlPeriodCbx.getSelectedItem();
        if (!(selectedItem instanceof PayrollPeriod)) {
            throw new IllegalArgumentException("Please select a valid payroll period.");
        }
        
        try {
            int month = financeGenPrlMChsr.getMonth() + 1; // 0 indexed
            int year = financeGenPrlYChsr.getYear();
            PayrollPeriod selectedPeriod = (PayrollPeriod) financeGenPrlPrlPeriodCbx.getSelectedItem();
            
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
        financeGenPrlBasicSalaryFld.setText(String.valueOf(emp.getBasicSalary()));
        financeGenPrlOvertimeFld.setText("");
        financeGenPrlHrsWorkedFld.setText(String.valueOf(payslip.getTotalHours()));
        financeGenPrlHourlyRateFld.setText(String.valueOf(emp.getHourlyRate()));
        financeGenPrlRiceSubsidyFld.setText(String.valueOf(payslip.getAllowanceBreakdown().getRiceSubsidy()));
        financeGenPrlPhnAllowanceFld.setText(String.valueOf(payslip.getAllowanceBreakdown().getPhoneAllowance()));
        financeGenPrlCltAllowanceFld.setText(String.valueOf(payslip.getAllowanceBreakdown().getClothingAllowance()));

        financeGenPrlTGrossFld.setText(String.valueOf(payslip.getGrossPay()));



        financeGenPrlSSSFld.setText(String.valueOf(payslip.getDeductionBreakdown().getSss()));
        financeGenPrlPagIbigFld.setText(String.valueOf(payslip.getDeductionBreakdown().getPhilHealth()));
        financeGenPrlPhilHealthFld.setText(String.valueOf(payslip.getDeductionBreakdown().getPagIbig()));
        financeGenPrlWithtaxFld.setText(String.valueOf(payslip.getDeductionBreakdown().getWithholdingTax()));
        financeGenPrlUndertimeFld.setText("");
        financeGenPrlTDeductionFld.setText(String.valueOf(payslip.getDeductionBreakdown().getTotal()));

        financeGenPrlNetPayFld.setText(String.valueOf(payslip.getNetPay()));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        financeGenPrlENumberFld = new javax.swing.JTextField();
        financeGenPrlENameLbl = new javax.swing.JLabel();
        financeGenPrlPrlDateLbl = new javax.swing.JLabel();
        financeGenPrlENameFld = new javax.swing.JTextField();
        financeGenPrlPrlPeriodLbl = new javax.swing.JLabel();
        financeGenPrlGenPrlPnl = new javax.swing.JPanel();
        financeGenPrlGenPrlLbl = new javax.swing.JLabel();
        financeGenPrlSidebarBtn = new javax.swing.JPanel();
        financeGenPrlMainDashboardBtn = new javax.swing.JButton();
        financeGenPrlMotorPHIconImgLbl = new javax.swing.JLabel();
        financeGenPrlPrlDetailsBtn = new javax.swing.JButton();
        financeGenPrlGenPrlBtn = new javax.swing.JButton();
        financeGenPrlPrlDListBtn = new javax.swing.JButton();
        financeGenPrlGenerateBtn = new javax.swing.JButton();
        financeGenPrlENumberLbl = new javax.swing.JLabel();
        financeGenPrlUploadBtn = new javax.swing.JButton();
        financeGenPrlPrlPeriodCbx = new javax.swing.JComboBox<>();
        financeGenPrlSCalculatorBrdrPnl = new javax.swing.JPanel();
        financeGenPrlSCalculatorLbl = new javax.swing.JLabel();
        financeGenPrlTGrossBrdrPnl = new javax.swing.JPanel();
        financeGenPrlEarningLbl = new javax.swing.JLabel();
        financeGenPrlBasicSalaryLbl = new javax.swing.JLabel();
        financeGenPrlOvertimeLbl = new javax.swing.JLabel();
        financeGenPrlHrsWorkedLbl = new javax.swing.JLabel();
        financeGenPrlBenefitLbl = new javax.swing.JLabel();
        financeGenPrlRiceSubsidyLbl = new javax.swing.JLabel();
        financeGenPrlPhnAllowanceLbl = new javax.swing.JLabel();
        financeGenPrlCltAllowanceLbl = new javax.swing.JLabel();
        financeGenPrlBasicSalaryFld = new javax.swing.JTextField();
        financeGenPrlOvertimeFld = new javax.swing.JTextField();
        financeGenPrlHrsWorkedFld = new javax.swing.JTextField();
        financeGenPrlRiceSubsidyFld = new javax.swing.JTextField();
        financeGenPrlPhnAllowanceFld = new javax.swing.JTextField();
        financeGenPrlCltAllowanceFld = new javax.swing.JTextField();
        financeGenPrlTGrossFld = new javax.swing.JTextField();
        financeGenPrlTGrossLbl = new javax.swing.JLabel();
        financeGenPrlHourlyRateLbl = new javax.swing.JLabel();
        financeGenPrlHourlyRateFld = new javax.swing.JTextField();
        financeGPrlTDeductionBrdrPnl = new javax.swing.JPanel();
        financeGenPrlDeductionLbl = new javax.swing.JLabel();
        financeGenPrlSSSLbl = new javax.swing.JLabel();
        financeGenPrlWithTaxLbl = new javax.swing.JLabel();
        financeGenPrlPhilHealthLbl = new javax.swing.JLabel();
        financeGenPrlUndertimeLbl = new javax.swing.JLabel();
        financeGenPrlSSSFld = new javax.swing.JTextField();
        financeGenPrlWithtaxFld = new javax.swing.JTextField();
        financeGenPrlPhilHealthFld = new javax.swing.JTextField();
        financeGenPrlUndertimeFld = new javax.swing.JTextField();
        financeGenPrlTDeductionFld = new javax.swing.JTextField();
        financeGenPrlTDeductionLbl = new javax.swing.JLabel();
        financeGenPrlPagIbigFld = new javax.swing.JTextField();
        financeGenPrlPagIbigLbl = new javax.swing.JLabel();
        financeGenPrlNetPayLbl = new javax.swing.JLabel();
        financeGenPrlNetPayFld = new javax.swing.JTextField();
        financeGenPrlMChsr = new com.toedter.calendar.JMonthChooser();
        financeGenPrlYChsr = new com.toedter.calendar.JYearChooser();
        financeGenPrlSearchPnl = new javax.swing.JPanel();
        financeGenPrlEntENumberFld = new javax.swing.JTextField();
        financeGenPrlSearchIconImgLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        financeGenPrlENumberFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlENumberFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlENumberFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlENumberFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlENumberFld.setEnabled(false);
        financeGenPrlENumberFld.addActionListener(this::financeGenPrlENumberFldActionPerformed);

        financeGenPrlENameLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlENameLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlENameLbl.setText("Employee Name");

        financeGenPrlPrlDateLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlPrlDateLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlPrlDateLbl.setText("Payroll Date");

        financeGenPrlENameFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlENameFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlENameFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlENameFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlENameFld.setEnabled(false);
        financeGenPrlENameFld.addActionListener(this::financeGenPrlENameFldActionPerformed);

        financeGenPrlPrlPeriodLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlPrlPeriodLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlPrlPeriodLbl.setText("Payroll Period");

        financeGenPrlGenPrlPnl.setBackground(new java.awt.Color(30, 58, 138));
        financeGenPrlGenPrlPnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlGenPrlPnl.setForeground(new java.awt.Color(30, 58, 138));

        financeGenPrlGenPrlLbl.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        financeGenPrlGenPrlLbl.setForeground(new java.awt.Color(255, 255, 255));
        financeGenPrlGenPrlLbl.setText("Generate Payroll");

        javax.swing.GroupLayout financeGenPrlGenPrlPnlLayout = new javax.swing.GroupLayout(financeGenPrlGenPrlPnl);
        financeGenPrlGenPrlPnl.setLayout(financeGenPrlGenPrlPnlLayout);
        financeGenPrlGenPrlPnlLayout.setHorizontalGroup(
            financeGenPrlGenPrlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financeGenPrlGenPrlPnlLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(financeGenPrlGenPrlLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        financeGenPrlGenPrlPnlLayout.setVerticalGroup(
            financeGenPrlGenPrlPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(financeGenPrlGenPrlLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        financeGenPrlGenPrlLbl.getAccessibleContext().setAccessibleName("financePrlGPrlLbl");

        financeGenPrlSidebarBtn.setBackground(new java.awt.Color(30, 58, 138));
        financeGenPrlSidebarBtn.setPreferredSize(new java.awt.Dimension(262, 700));

        financeGenPrlMainDashboardBtn.setBackground(new java.awt.Color(30, 42, 56));
        financeGenPrlMainDashboardBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGenPrlMainDashboardBtn.setForeground(new java.awt.Color(255, 255, 255));
        financeGenPrlMainDashboardBtn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        financeGenPrlMainDashboardBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        financeGenPrlMainDashboardBtn.setLabel("Main Dashboard");
        financeGenPrlMainDashboardBtn.addActionListener(this::financeGenPrlMainDashboardBtnActionPerformed);

        financeGenPrlMotorPHIconImgLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorph/img/MotorPHIconImg.png"))); // NOI18N

        financeGenPrlPrlDetailsBtn.setBackground(new java.awt.Color(30, 42, 56));
        financeGenPrlPrlDetailsBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGenPrlPrlDetailsBtn.setForeground(new java.awt.Color(255, 255, 255));
        financeGenPrlPrlDetailsBtn.setText("Payroll Records");
        financeGenPrlPrlDetailsBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        financeGenPrlPrlDetailsBtn.addActionListener(this::financeGenPrlPrlDetailsBtnActionPerformed);

        financeGenPrlGenPrlBtn.setBackground(new java.awt.Color(30, 42, 56));
        financeGenPrlGenPrlBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGenPrlGenPrlBtn.setForeground(new java.awt.Color(255, 255, 255));
        financeGenPrlGenPrlBtn.setText("Generate Payroll");
        financeGenPrlGenPrlBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        financeGenPrlGenPrlBtn.addActionListener(this::financeGenPrlGenPrlBtnActionPerformed);

        financeGenPrlPrlDListBtn.setBackground(new java.awt.Color(30, 42, 56));
        financeGenPrlPrlDListBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGenPrlPrlDListBtn.setForeground(new java.awt.Color(255, 255, 255));
        financeGenPrlPrlDListBtn.setText("Payroll Dispute List");
        financeGenPrlPrlDListBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        financeGenPrlPrlDListBtn.addActionListener(this::financeGenPrlPrlDListBtnActionPerformed);

        javax.swing.GroupLayout financeGenPrlSidebarBtnLayout = new javax.swing.GroupLayout(financeGenPrlSidebarBtn);
        financeGenPrlSidebarBtn.setLayout(financeGenPrlSidebarBtnLayout);
        financeGenPrlSidebarBtnLayout.setHorizontalGroup(
            financeGenPrlSidebarBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financeGenPrlSidebarBtnLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(financeGenPrlMainDashboardBtn))
            .addGroup(financeGenPrlSidebarBtnLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(financeGenPrlSidebarBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(financeGenPrlPrlDListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGenPrlGenPrlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(financeGenPrlSidebarBtnLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(financeGenPrlMotorPHIconImgLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(financeGenPrlPrlDetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        financeGenPrlSidebarBtnLayout.setVerticalGroup(
            financeGenPrlSidebarBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financeGenPrlSidebarBtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(financeGenPrlSidebarBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(financeGenPrlMotorPHIconImgLbl)
                    .addGroup(financeGenPrlSidebarBtnLayout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(financeGenPrlPrlDetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(financeGenPrlGenPrlBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(financeGenPrlPrlDListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(307, 307, 307)
                .addComponent(financeGenPrlMainDashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        financeGenPrlMainDashboardBtn.getAccessibleContext().setAccessibleName("financePrlMainDashboardBtn");
        financeGenPrlMotorPHIconImgLbl.getAccessibleContext().setAccessibleName("financePrlMotorPHIconImgLbl");
        financeGenPrlPrlDetailsBtn.getAccessibleContext().setAccessibleName("financePrlPrlRecordDetailsBtn");
        financeGenPrlGenPrlBtn.getAccessibleContext().setAccessibleName("financePrlGPrlBtn");
        financeGenPrlPrlDListBtn.getAccessibleContext().setAccessibleName("financeGPrlPrlDListBtn");

        financeGenPrlGenerateBtn.setBackground(new java.awt.Color(30, 58, 138));
        financeGenPrlGenerateBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGenPrlGenerateBtn.setForeground(new java.awt.Color(255, 255, 255));
        financeGenPrlGenerateBtn.setText("Generate");
        financeGenPrlGenerateBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlGenerateBtn.addActionListener(this::financeGenPrlGenerateBtnActionPerformed);

        financeGenPrlENumberLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlENumberLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlENumberLbl.setText("Employee #");

        financeGenPrlUploadBtn.setBackground(new java.awt.Color(34, 197, 94));
        financeGenPrlUploadBtn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGenPrlUploadBtn.setForeground(new java.awt.Color(255, 255, 255));
        financeGenPrlUploadBtn.setText("Upload");
        financeGenPrlUploadBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlUploadBtn.addActionListener(this::financeGenPrlUploadBtnActionPerformed);

        financeGenPrlPrlPeriodCbx.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlPrlPeriodCbx.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(31, 41, 55), 1, true));

        financeGenPrlSCalculatorBrdrPnl.setBackground(new java.awt.Color(146, 192, 253));
        financeGenPrlSCalculatorBrdrPnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlSCalculatorBrdrPnl.setForeground(new java.awt.Color(146, 192, 253));
        financeGenPrlSCalculatorBrdrPnl.setPreferredSize(new java.awt.Dimension(710, 408));

        financeGenPrlSCalculatorLbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGenPrlSCalculatorLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlSCalculatorLbl.setText("Salary Calculator");

        financeGenPrlTGrossBrdrPnl.setBackground(new java.awt.Color(233, 233, 233));
        financeGenPrlTGrossBrdrPnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlTGrossBrdrPnl.setForeground(new java.awt.Color(30, 58, 138));

        financeGenPrlEarningLbl.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        financeGenPrlEarningLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlEarningLbl.setText("Earning");

        financeGenPrlBasicSalaryLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlBasicSalaryLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlBasicSalaryLbl.setText("Basic Salary");

        financeGenPrlOvertimeLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlOvertimeLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlOvertimeLbl.setText("Overtime");

        financeGenPrlHrsWorkedLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlHrsWorkedLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlHrsWorkedLbl.setText("Hour/s Worked");

        financeGenPrlBenefitLbl.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        financeGenPrlBenefitLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlBenefitLbl.setText("Benefit");

        financeGenPrlRiceSubsidyLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlRiceSubsidyLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlRiceSubsidyLbl.setText("Rice Subsidy");

        financeGenPrlPhnAllowanceLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlPhnAllowanceLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlPhnAllowanceLbl.setText("Phone Allowance");

        financeGenPrlCltAllowanceLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlCltAllowanceLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlCltAllowanceLbl.setText("Clothing Allowance");

        financeGenPrlBasicSalaryFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlBasicSalaryFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlBasicSalaryFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlBasicSalaryFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlBasicSalaryFld.setEnabled(false);
        financeGenPrlBasicSalaryFld.addActionListener(this::financeGenPrlBasicSalaryFldActionPerformed);

        financeGenPrlOvertimeFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlOvertimeFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlOvertimeFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlOvertimeFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlOvertimeFld.setEnabled(false);
        financeGenPrlOvertimeFld.addActionListener(this::financeGenPrlOvertimeFldActionPerformed);

        financeGenPrlHrsWorkedFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlHrsWorkedFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlHrsWorkedFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlHrsWorkedFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlHrsWorkedFld.setEnabled(false);
        financeGenPrlHrsWorkedFld.addActionListener(this::financeGenPrlHrsWorkedFldActionPerformed);

        financeGenPrlRiceSubsidyFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlRiceSubsidyFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlRiceSubsidyFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlRiceSubsidyFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlRiceSubsidyFld.setEnabled(false);
        financeGenPrlRiceSubsidyFld.addActionListener(this::financeGenPrlRiceSubsidyFldActionPerformed);

        financeGenPrlPhnAllowanceFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlPhnAllowanceFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlPhnAllowanceFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlPhnAllowanceFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlPhnAllowanceFld.setEnabled(false);
        financeGenPrlPhnAllowanceFld.addActionListener(this::financeGenPrlPhnAllowanceFldActionPerformed);

        financeGenPrlCltAllowanceFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlCltAllowanceFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlCltAllowanceFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlCltAllowanceFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlCltAllowanceFld.setEnabled(false);
        financeGenPrlCltAllowanceFld.addActionListener(this::financeGenPrlCltAllowanceFldActionPerformed);

        financeGenPrlTGrossFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlTGrossFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlTGrossFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlTGrossFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlTGrossFld.setEnabled(false);
        financeGenPrlTGrossFld.addActionListener(this::financeGenPrlTGrossFldActionPerformed);

        financeGenPrlTGrossLbl.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        financeGenPrlTGrossLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlTGrossLbl.setText("Total Gross");

        financeGenPrlHourlyRateLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlHourlyRateLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlHourlyRateLbl.setText("Hourly Rate");

        financeGenPrlHourlyRateFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlHourlyRateFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlHourlyRateFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlHourlyRateFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlHourlyRateFld.setEnabled(false);
        financeGenPrlHourlyRateFld.addActionListener(this::financeGenPrlHourlyRateFldActionPerformed);

        javax.swing.GroupLayout financeGenPrlTGrossBrdrPnlLayout = new javax.swing.GroupLayout(financeGenPrlTGrossBrdrPnl);
        financeGenPrlTGrossBrdrPnl.setLayout(financeGenPrlTGrossBrdrPnlLayout);
        financeGenPrlTGrossBrdrPnlLayout.setHorizontalGroup(
            financeGenPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGenPrlTGrossBrdrPnlLayout.createSequentialGroup()
                .addGroup(financeGenPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, financeGenPrlTGrossBrdrPnlLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addGroup(financeGenPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(financeGenPrlEarningLbl)
                            .addComponent(financeGenPrlBenefitLbl))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, financeGenPrlTGrossBrdrPnlLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(financeGenPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(financeGenPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGenPrlHourlyRateLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGenPrlHourlyRateFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGenPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGenPrlOvertimeLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGenPrlOvertimeFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGenPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGenPrlBasicSalaryLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGenPrlBasicSalaryFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGenPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGenPrlHrsWorkedLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(financeGenPrlHrsWorkedFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGenPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGenPrlRiceSubsidyLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGenPrlRiceSubsidyFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGenPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(financeGenPrlTGrossLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(financeGenPrlTGrossFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGenPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGenPrlCltAllowanceLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGenPrlCltAllowanceFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGenPrlTGrossBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGenPrlPhnAllowanceLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGenPrlPhnAllowanceFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(26, 26, 26))
        );
        financeGenPrlTGrossBrdrPnlLayout.setVerticalGroup(
            financeGenPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financeGenPrlTGrossBrdrPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(financeGenPrlEarningLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGenPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlBasicSalaryLbl)
                    .addComponent(financeGenPrlBasicSalaryFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGenPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlOvertimeLbl)
                    .addComponent(financeGenPrlOvertimeFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGenPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlHrsWorkedLbl)
                    .addComponent(financeGenPrlHrsWorkedFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGenPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlHourlyRateLbl)
                    .addComponent(financeGenPrlHourlyRateFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(financeGenPrlBenefitLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGenPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(financeGenPrlRiceSubsidyFld, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGenPrlRiceSubsidyLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGenPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlPhnAllowanceFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGenPrlPhnAllowanceLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGenPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlCltAllowanceFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGenPrlCltAllowanceLbl))
                .addGap(28, 28, 28)
                .addGroup(financeGenPrlTGrossBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlTGrossLbl)
                    .addComponent(financeGenPrlTGrossFld, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        financeGPrlTDeductionBrdrPnl.setBackground(new java.awt.Color(233, 233, 233));
        financeGPrlTDeductionBrdrPnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGPrlTDeductionBrdrPnl.setForeground(new java.awt.Color(30, 58, 138));

        financeGenPrlDeductionLbl.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        financeGenPrlDeductionLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlDeductionLbl.setText("Deduction");

        financeGenPrlSSSLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlSSSLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlSSSLbl.setText("SSS");

        financeGenPrlWithTaxLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlWithTaxLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlWithTaxLbl.setText("Withholding Tax");

        financeGenPrlPhilHealthLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlPhilHealthLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlPhilHealthLbl.setText("PhilHealth");

        financeGenPrlUndertimeLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlUndertimeLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlUndertimeLbl.setText("Undertime");

        financeGenPrlSSSFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlSSSFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlSSSFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlSSSFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlSSSFld.setEnabled(false);
        financeGenPrlSSSFld.addActionListener(this::financeGenPrlSSSFldActionPerformed);

        financeGenPrlWithtaxFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlWithtaxFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlWithtaxFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlWithtaxFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlWithtaxFld.setEnabled(false);
        financeGenPrlWithtaxFld.addActionListener(this::financeGenPrlWithtaxFldActionPerformed);

        financeGenPrlPhilHealthFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlPhilHealthFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlPhilHealthFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlPhilHealthFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlPhilHealthFld.setEnabled(false);
        financeGenPrlPhilHealthFld.addActionListener(this::financeGenPrlPhilHealthFldActionPerformed);

        financeGenPrlUndertimeFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlUndertimeFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlUndertimeFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlUndertimeFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlUndertimeFld.setEnabled(false);
        financeGenPrlUndertimeFld.addActionListener(this::financeGenPrlUndertimeFldActionPerformed);

        financeGenPrlTDeductionFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlTDeductionFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlTDeductionFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlTDeductionFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlTDeductionFld.setEnabled(false);
        financeGenPrlTDeductionFld.addActionListener(this::financeGenPrlTDeductionFldActionPerformed);

        financeGenPrlTDeductionLbl.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        financeGenPrlTDeductionLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlTDeductionLbl.setText("Total Deduction");

        financeGenPrlPagIbigFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlPagIbigFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlPagIbigFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlPagIbigFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlPagIbigFld.setEnabled(false);
        financeGenPrlPagIbigFld.addActionListener(this::financeGenPrlPagIbigFldActionPerformed);

        financeGenPrlPagIbigLbl.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlPagIbigLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlPagIbigLbl.setText("Pag-Ibig");

        javax.swing.GroupLayout financeGPrlTDeductionBrdrPnlLayout = new javax.swing.GroupLayout(financeGPrlTDeductionBrdrPnl);
        financeGPrlTDeductionBrdrPnl.setLayout(financeGPrlTDeductionBrdrPnlLayout);
        financeGPrlTDeductionBrdrPnlLayout.setHorizontalGroup(
            financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(financeGenPrlDeductionLbl)
                        .addGap(0, 97, Short.MAX_VALUE))
                    .addGroup(financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(financeGenPrlTDeductionLbl)
                        .addGap(18, 18, 18)
                        .addComponent(financeGenPrlTDeductionFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGenPrlPagIbigLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGenPrlPagIbigFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGenPrlUndertimeLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGenPrlUndertimeFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGenPrlSSSLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGenPrlSSSFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGenPrlPhilHealthLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGenPrlPhilHealthFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                                .addComponent(financeGenPrlWithTaxLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGenPrlWithtaxFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        financeGPrlTDeductionBrdrPnlLayout.setVerticalGroup(
            financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGPrlTDeductionBrdrPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(financeGenPrlDeductionLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlSSSLbl)
                    .addComponent(financeGenPrlSSSFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlPagIbigFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGenPrlPagIbigLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlPhilHealthLbl)
                    .addComponent(financeGenPrlPhilHealthFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlWithTaxLbl)
                    .addComponent(financeGenPrlWithtaxFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlUndertimeLbl)
                    .addComponent(financeGenPrlUndertimeFld, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(financeGPrlTDeductionBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlTDeductionFld, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGenPrlTDeductionLbl))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        financeGenPrlNetPayLbl.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        financeGenPrlNetPayLbl.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlNetPayLbl.setText("Net Pay");

        financeGenPrlNetPayFld.setBackground(new java.awt.Color(34, 197, 94));
        financeGenPrlNetPayFld.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        financeGenPrlNetPayFld.setForeground(new java.awt.Color(255, 255, 255));
        financeGenPrlNetPayFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlNetPayFld.setCaretColor(new java.awt.Color(255, 255, 255));
        financeGenPrlNetPayFld.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        financeGenPrlNetPayFld.setEnabled(false);
        financeGenPrlNetPayFld.addActionListener(this::financeGenPrlNetPayFldActionPerformed);

        javax.swing.GroupLayout financeGenPrlSCalculatorBrdrPnlLayout = new javax.swing.GroupLayout(financeGenPrlSCalculatorBrdrPnl);
        financeGenPrlSCalculatorBrdrPnl.setLayout(financeGenPrlSCalculatorBrdrPnlLayout);
        financeGenPrlSCalculatorBrdrPnlLayout.setHorizontalGroup(
            financeGenPrlSCalculatorBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financeGenPrlSCalculatorBrdrPnlLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(financeGenPrlTGrossBrdrPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(financeGenPrlSCalculatorBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(financeGPrlTDeductionBrdrPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGenPrlSCalculatorBrdrPnlLayout.createSequentialGroup()
                        .addComponent(financeGenPrlNetPayLbl)
                        .addGap(32, 32, 32)
                        .addComponent(financeGenPrlNetPayFld, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeGenPrlSCalculatorBrdrPnlLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(financeGenPrlSCalculatorLbl)
                .addGap(283, 283, 283))
        );
        financeGenPrlSCalculatorBrdrPnlLayout.setVerticalGroup(
            financeGenPrlSCalculatorBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(financeGenPrlSCalculatorBrdrPnlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(financeGenPrlSCalculatorLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(financeGenPrlSCalculatorBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(financeGenPrlSCalculatorBrdrPnlLayout.createSequentialGroup()
                        .addComponent(financeGPrlTDeductionBrdrPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(financeGenPrlSCalculatorBrdrPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(financeGenPrlNetPayFld, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(financeGenPrlNetPayLbl)))
                    .addComponent(financeGenPrlTGrossBrdrPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        financeGenPrlSCalculatorLbl.getAccessibleContext().setAccessibleName("financeGPrlSCalculatorLbl");

        financeGenPrlMChsr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));

        financeGenPrlYChsr.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));

        financeGenPrlSearchPnl.setBackground(new java.awt.Color(146, 192, 253));
        financeGenPrlSearchPnl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlSearchPnl.setForeground(new java.awt.Color(146, 192, 253));
        financeGenPrlSearchPnl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        financeGenPrlEntENumberFld.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        financeGenPrlEntENumberFld.setForeground(new java.awt.Color(31, 41, 55));
        financeGenPrlEntENumberFld.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(30, 42, 56), 1, true));
        financeGenPrlEntENumberFld.setCaretColor(new java.awt.Color(31, 41, 55));
        financeGenPrlEntENumberFld.setDisabledTextColor(new java.awt.Color(31, 41, 55));
        financeGenPrlEntENumberFld.addActionListener(this::financeGenPrlEntENumberFldActionPerformed);
        financeGenPrlSearchPnl.add(financeGenPrlEntENumberFld, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 170, 31));

        financeGenPrlSearchIconImgLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorph/img/SearchIconImg.png"))); // NOI18N
        financeGenPrlSearchPnl.add(financeGenPrlSearchIconImgLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, -10, 60, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(financeGenPrlSidebarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(financeGenPrlGenPrlPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(financeGenPrlSearchPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(financeGenPrlSCalculatorBrdrPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(financeGenPrlGenerateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(financeGenPrlUploadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(financeGenPrlENameLbl)
                                .addGap(18, 18, 18)
                                .addComponent(financeGenPrlENameFld, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(financeGenPrlENumberLbl)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(financeGenPrlENumberFld, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(financeGenPrlPrlPeriodLbl)
                            .addComponent(financeGenPrlPrlDateLbl))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(financeGenPrlMChsr, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(financeGenPrlYChsr, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(financeGenPrlPrlPeriodCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(financeGenPrlGenPrlPnl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGenPrlSearchPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(financeGenPrlPrlDateLbl)
                            .addGap(15, 15, 15)
                            .addComponent(financeGenPrlPrlPeriodLbl))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(financeGenPrlMChsr, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(financeGenPrlYChsr, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(9, 9, 9)
                            .addComponent(financeGenPrlPrlPeriodCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(financeGenPrlENumberFld, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(financeGenPrlENumberLbl))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(financeGenPrlENameFld, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(financeGenPrlENameLbl))))
                .addGap(18, 18, 18)
                .addComponent(financeGenPrlSCalculatorBrdrPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(financeGenPrlGenerateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeGenPrlUploadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(financeGenPrlSidebarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        financeGenPrlENumberFld.getAccessibleContext().setAccessibleName("financeGPrlENumberFld");
        financeGenPrlENameLbl.getAccessibleContext().setAccessibleName("financePrlENameLbl");
        financeGenPrlPrlDateLbl.getAccessibleContext().setAccessibleName("financePrlPrlDateLbl");
        financeGenPrlENameFld.getAccessibleContext().setAccessibleName("financePrlENameFld");
        financeGenPrlPrlPeriodLbl.getAccessibleContext().setAccessibleName("financePrlPrlPeriodLbl");
        financeGenPrlGenPrlPnl.getAccessibleContext().setAccessibleName("financePrlGPrlPnl");
        financeGenPrlSidebarBtn.getAccessibleContext().setAccessibleName("financePrlSidebarBtn");
        financeGenPrlGenerateBtn.getAccessibleContext().setAccessibleName("financeGPrlGenerateBtn");
        financeGenPrlENumberLbl.getAccessibleContext().setAccessibleName("financePrlENumberLbl");
        financeGenPrlUploadBtn.getAccessibleContext().setAccessibleName("financeGPrlUploadBtn");
        financeGenPrlPrlPeriodCbx.getAccessibleContext().setAccessibleName("financePrlPrlPeriodCbx");
        financeGenPrlSCalculatorBrdrPnl.getAccessibleContext().setAccessibleName("financeGPrlSCalculatorBrdrPnl");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void financeGenPrlENumberFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlENumberFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlENumberFldActionPerformed

    private void financeGenPrlENameFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlENameFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlENameFldActionPerformed

    private void financeGenPrlMainDashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlMainDashboardBtnActionPerformed
        // TODO add your handling code here:
        GuiUtil.openFrame(this);
    }//GEN-LAST:event_financeGenPrlMainDashboardBtnActionPerformed

    private void financeGenPrlPrlDetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlPrlDetailsBtnActionPerformed
        // TODO add your handling code here:
        GuiUtil.openFrame(this, new FinancePayrollRecordsUI());
    }//GEN-LAST:event_financeGenPrlPrlDetailsBtnActionPerformed

    private void financeGenPrlGenerateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlGenerateBtnActionPerformed
        // TODO add your handling code here:
        try {
            String employeeNumber = financeGenPrlENumberFld.getText().trim();
            if (employeeNumber == null || employeeNumber.isBlank() || employeeNumber.isEmpty()) {
                throw new IllegalArgumentException("Search for an Employee first.");
            }
            
            
            Employee emp = empService.findEmployee(employeeNumber);
            this.payslip = generatePayslip(emp);
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
       
    }//GEN-LAST:event_financeGenPrlGenerateBtnActionPerformed

    private void financeGenPrlUploadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlUploadBtnActionPerformed
        // TODO add your handling code here:
        try {
            
            payrollService.savePayslip(payslip);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }//GEN-LAST:event_financeGenPrlUploadBtnActionPerformed

    private void financeGenPrlBasicSalaryFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlBasicSalaryFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlBasicSalaryFldActionPerformed

    private void financeGenPrlOvertimeFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlOvertimeFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlOvertimeFldActionPerformed

    private void financeGenPrlHrsWorkedFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlHrsWorkedFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlHrsWorkedFldActionPerformed

    private void financeGenPrlRiceSubsidyFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlRiceSubsidyFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlRiceSubsidyFldActionPerformed

    private void financeGenPrlPhnAllowanceFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlPhnAllowanceFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlPhnAllowanceFldActionPerformed

    private void financeGenPrlCltAllowanceFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlCltAllowanceFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlCltAllowanceFldActionPerformed

    private void financeGenPrlTGrossFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlTGrossFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlTGrossFldActionPerformed

    private void financeGenPrlHourlyRateFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlHourlyRateFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlHourlyRateFldActionPerformed

    private void financeGenPrlSSSFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlSSSFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlSSSFldActionPerformed

    private void financeGenPrlWithtaxFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlWithtaxFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlWithtaxFldActionPerformed

    private void financeGenPrlPhilHealthFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlPhilHealthFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlPhilHealthFldActionPerformed

    private void financeGenPrlUndertimeFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlUndertimeFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlUndertimeFldActionPerformed

    private void financeGenPrlTDeductionFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlTDeductionFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlTDeductionFldActionPerformed

    private void financeGenPrlPagIbigFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlPagIbigFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlPagIbigFldActionPerformed

    private void financeGenPrlNetPayFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlNetPayFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlNetPayFldActionPerformed

    private void financeGenPrlGenPrlBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlGenPrlBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_financeGenPrlGenPrlBtnActionPerformed

    private void financeGenPrlPrlDListBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlPrlDListBtnActionPerformed
        // TODO add your handling code here:
        GuiUtil.openFrame(this, new FinancePayrollDisputeList());
    }//GEN-LAST:event_financeGenPrlPrlDListBtnActionPerformed

    private void financeGenPrlEntENumberFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeGenPrlEntENumberFldActionPerformed
        // TODO add your handling code here:
        this.payslip = null;
        performSearch();
    }//GEN-LAST:event_financeGenPrlEntENumberFldActionPerformed

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
    private javax.swing.JPanel financeGPrlTDeductionBrdrPnl;
    private javax.swing.JTextField financeGenPrlBasicSalaryFld;
    private javax.swing.JLabel financeGenPrlBasicSalaryLbl;
    private javax.swing.JLabel financeGenPrlBenefitLbl;
    private javax.swing.JTextField financeGenPrlCltAllowanceFld;
    private javax.swing.JLabel financeGenPrlCltAllowanceLbl;
    private javax.swing.JLabel financeGenPrlDeductionLbl;
    private javax.swing.JTextField financeGenPrlENameFld;
    private javax.swing.JLabel financeGenPrlENameLbl;
    private javax.swing.JTextField financeGenPrlENumberFld;
    private javax.swing.JLabel financeGenPrlENumberLbl;
    private javax.swing.JLabel financeGenPrlEarningLbl;
    private javax.swing.JTextField financeGenPrlEntENumberFld;
    private javax.swing.JButton financeGenPrlGenPrlBtn;
    private javax.swing.JLabel financeGenPrlGenPrlLbl;
    private javax.swing.JPanel financeGenPrlGenPrlPnl;
    private javax.swing.JButton financeGenPrlGenerateBtn;
    private javax.swing.JTextField financeGenPrlHourlyRateFld;
    private javax.swing.JLabel financeGenPrlHourlyRateLbl;
    private javax.swing.JTextField financeGenPrlHrsWorkedFld;
    private javax.swing.JLabel financeGenPrlHrsWorkedLbl;
    private com.toedter.calendar.JMonthChooser financeGenPrlMChsr;
    private javax.swing.JButton financeGenPrlMainDashboardBtn;
    private javax.swing.JLabel financeGenPrlMotorPHIconImgLbl;
    private javax.swing.JTextField financeGenPrlNetPayFld;
    private javax.swing.JLabel financeGenPrlNetPayLbl;
    private javax.swing.JTextField financeGenPrlOvertimeFld;
    private javax.swing.JLabel financeGenPrlOvertimeLbl;
    private javax.swing.JTextField financeGenPrlPagIbigFld;
    private javax.swing.JLabel financeGenPrlPagIbigLbl;
    private javax.swing.JTextField financeGenPrlPhilHealthFld;
    private javax.swing.JLabel financeGenPrlPhilHealthLbl;
    private javax.swing.JTextField financeGenPrlPhnAllowanceFld;
    private javax.swing.JLabel financeGenPrlPhnAllowanceLbl;
    private javax.swing.JButton financeGenPrlPrlDListBtn;
    private javax.swing.JLabel financeGenPrlPrlDateLbl;
    private javax.swing.JButton financeGenPrlPrlDetailsBtn;
    private javax.swing.JComboBox<Object> financeGenPrlPrlPeriodCbx;
    private javax.swing.JLabel financeGenPrlPrlPeriodLbl;
    private javax.swing.JTextField financeGenPrlRiceSubsidyFld;
    private javax.swing.JLabel financeGenPrlRiceSubsidyLbl;
    private javax.swing.JPanel financeGenPrlSCalculatorBrdrPnl;
    private javax.swing.JLabel financeGenPrlSCalculatorLbl;
    private javax.swing.JTextField financeGenPrlSSSFld;
    private javax.swing.JLabel financeGenPrlSSSLbl;
    private javax.swing.JLabel financeGenPrlSearchIconImgLbl;
    private javax.swing.JPanel financeGenPrlSearchPnl;
    private javax.swing.JPanel financeGenPrlSidebarBtn;
    private javax.swing.JTextField financeGenPrlTDeductionFld;
    private javax.swing.JLabel financeGenPrlTDeductionLbl;
    private javax.swing.JPanel financeGenPrlTGrossBrdrPnl;
    private javax.swing.JTextField financeGenPrlTGrossFld;
    private javax.swing.JLabel financeGenPrlTGrossLbl;
    private javax.swing.JTextField financeGenPrlUndertimeFld;
    private javax.swing.JLabel financeGenPrlUndertimeLbl;
    private javax.swing.JButton financeGenPrlUploadBtn;
    private javax.swing.JLabel financeGenPrlWithTaxLbl;
    private javax.swing.JTextField financeGenPrlWithtaxFld;
    private com.toedter.calendar.JYearChooser financeGenPrlYChsr;
    // End of variables declaration//GEN-END:variables
}

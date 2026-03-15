
package com.motorph.dao;

import com.motorph.model.Payslip;
import java.util.List;


public interface PayslipDAO {
    
    void savePayslip(Payslip payslip);
    
    List<Payslip> findPayslipsByEmployee(String employeeNumber);
    
    Payslip findPayslipById(String payslipId);
}

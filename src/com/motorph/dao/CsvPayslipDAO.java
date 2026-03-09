

package com.motorph.dao;

import com.motorph.model.Payslip;
import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class CsvPayslipDAO implements PayslipDAO {
    
    private static final String FILE_PATH = "";
    private List<Payslip> allPayslips;
    
    public CsvPayslipDAO() {
        
    }
    
    @Override
    public void savePayslip(Payslip payslip) {
        
        try (CSVReader reader = new CSVReader(new FileReader (FILE_PATH))) {
            
            String[] data;
            reader.readNext();
            
            while ((data = reader.readNext()) != null) {
            
            
            
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Payslip> findPayslipsByEmploye(String employeeNumber) {
        return allPayslips;
    }
}

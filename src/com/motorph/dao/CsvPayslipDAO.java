

package com.motorph.dao;

import com.motorph.model.AllowanceBreakdown;
import com.motorph.model.DeductionBreakdown;
import com.motorph.model.Payslip;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CsvPayslipDAO implements PayslipDAO {
    
    private static final String FILE_PATH = "data/payslips.csv";
    
    public CsvPayslipDAO() {
        
    }
    
    private void initializeFile() {
           try {
            File file = new File(FILE_PATH);
            
            // Create folder if missing
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            
            // if file is new: write header
            if (!file.exists()) {
                try(CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
                     // if file is new: write header
                    String[] header = {
                        "payslipId",
                        "employeeNumber",
                        "position",
                        "periodStart",
                        "periodEnd",
                        "totalHours",
                        "hourlyRate",
                        "grossPay",
                        "riceSubsidy",
                        "phoneAllowance",
                        "clothingAllowance",
                        "sss",
                        "philhealth",
                        "pagibig",
                        "tax",
                        "netPay"
                    };
                    writer.writeNext(header);
                }            
            }
        } catch (Exception e) {
            System.err.println("Could not initialize leave CSV: " + e.getMessage());
        }
    }

    public void savePayslip(Payslip payslip) {
        File file = new File(FILE_PATH);
        
        
        
        try (CSVWriter writer = new CSVWriter(new FileWriter(file, true))) {
            
 
           
            
            // Check for nulls in nested objects to prevent NullPointerException
            AllowanceBreakdown allowances = payslip.getAllowanceBreakdown();
            DeductionBreakdown deductions = payslip.getDeductionBreakdown();

            String[] row = {
                payslip.getPayslipId(),
                payslip.getEmployeeNumber(),
                payslip.getPosition(),
                payslip.getPeriodStart().toString(),
                payslip.getPeriodEnd().toString(),
                String.valueOf(payslip.getTotalHours()),
                String.valueOf(payslip.getHourlyRate()),
                String.valueOf(payslip.getGrossPay()),
                String.valueOf(allowances != null ? allowances.getRiceSubsidy() : 0),
                String.valueOf(allowances != null ? allowances.getPhoneAllowance() : 0),
                String.valueOf(allowances != null ? allowances.getClothingAllowance() : 0),
                String.valueOf(deductions != null ? deductions.getSss() : 0),
                String.valueOf(deductions != null ? deductions.getPhilHealth() : 0),
                String.valueOf(deductions != null ? deductions.getPagIbig() : 0),
                String.valueOf(deductions != null ? deductions.getWithholdingTax() : 0),
                String.valueOf(payslip.getNetPay())
            };
            
            writer.writeNext(row);
            
            
        } catch (Exception e) {
            System.err.println("Error saving payslip: " + e.getMessage());
            e.printStackTrace();
        }
        
         
    }
    
    public List<Payslip> findPayslipsByEmployee(String employeeNumber) {
        
        List<Payslip> payslips = new ArrayList<>();
        
        File file = new File(FILE_PATH);

        // 3. Return empty list if file doesn't exist yet (prevents FileReader error)
        if (!file.exists()) {
            return payslips;
        }
        
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            reader.readNext(); // skip header
            
            String[] row;
            while ((row = reader.readNext()) != null) {
                // Check if row has enough columns and matches employee ID
                if (row.length >= 16 && row[1].equals(employeeNumber)) {
                    
                    // Reconstruct AllowanceBreakdown
                    AllowanceBreakdown allowanceBreakdown =
                        new AllowanceBreakdown(
                                Double.parseDouble(row[8]),
                                Double.parseDouble(row[9]),
                                Double.parseDouble(row[10])
                        );
                    
                    // Reconstruct DeductionBreakdown
                    DeductionBreakdown deductionBreakdown =
                        new DeductionBreakdown(
                                Double.parseDouble(row[11]),
                                Double.parseDouble(row[12]),
                                Double.parseDouble(row[13]),
                                Double.parseDouble(row[14])
                        );
                    
                    // reconstruct Payslip object
                    Payslip p = new Payslip(
                            row[0],
                            row[1],
                            row[2],
                            LocalDate.parse(row[3]),
                            LocalDate.parse(row[4]),
                            Double.parseDouble(row[5]),
                            Double.parseDouble(row[6]),
                            Double.parseDouble(row[7]),
                            allowanceBreakdown,
                            deductionBreakdown,
                            Double.parseDouble(row[15])
                    );
                    
                    payslips.add(p);             
                }
            }
            
            
        } catch (Exception e) {
            System.err.println("Error reading payslips: " + e.getMessage());
            e.printStackTrace();
        }
        
        return payslips;
    }
}

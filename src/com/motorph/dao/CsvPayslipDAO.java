

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
        initializeFile();
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
                        "employeeName",
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
            System.err.println("Could not initialize payslip CSV: " + e.getMessage());
        }
    }

    @Override
    public void savePayslip(Payslip payslip) {
        
        // Duplicate check:
        if (payslipExists(payslip.getPayslipId())) {
            System.out.println("Payslip already exists: " + payslip.getPayslipId()  );
            return;
        }
        
        
        File file = new File(FILE_PATH);
        

        try (CSVWriter writer = new CSVWriter(new FileWriter(file, true))) {
            

            // Check for nulls in nested objects to prevent NullPointerException
            AllowanceBreakdown allowances = payslip.getAllowanceBreakdown();
            DeductionBreakdown deductions = payslip.getDeductionBreakdown();

            String[] row = {
                payslip.getPayslipId(),
                payslip.getEmployeeNumber(),
                payslip.getEmployeeName(),
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
    
    @Override
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
                if (row.length >= 17 && row[1].equals(employeeNumber)) {
                    
                    // Reconstruct AllowanceBreakdown
                    AllowanceBreakdown allowanceBreakdown =
                        new AllowanceBreakdown(
                                Double.parseDouble(row[9]),
                                Double.parseDouble(row[10]),
                                Double.parseDouble(row[11])
                        );
                    
                    // Reconstruct DeductionBreakdown
                    DeductionBreakdown deductionBreakdown =
                        new DeductionBreakdown(
                                Double.parseDouble(row[12]),
                                Double.parseDouble(row[13]),
                                Double.parseDouble(row[14]),
                                Double.parseDouble(row[15])
                        );
                    
                    // reconstruct Payslip object
                    Payslip p = new Payslip(
                            row[0],
                            row[1],
                            row[2],
                            row[3],
                            LocalDate.parse(row[4]),
                            LocalDate.parse(row[5]),
                            Double.parseDouble(row[6]),
                            Double.parseDouble(row[7]),
                            Double.parseDouble(row[8]),
                            allowanceBreakdown,
                            deductionBreakdown,
                            Double.parseDouble(row[16])
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
    
    // Check if payslip already exists
    public boolean payslipExists(String payslipId) {        
        File file = new File(FILE_PATH);        
        if (!file.exists()) return false;        
        try (CSVReader reader = new CSVReader(new FileReader(file))) {            
            reader.readNext(); // skip header            
            String[] row;
            
            while ((row = reader.readNext()) != null) {
                if (row.length > 0 && row[0].equals(payslipId)) {
                    return true;
                }
            }            
        } catch (Exception e) {
            System.err.println("Error checking duplicate payslip: " + e.getMessage());
        }
        
        return false;
    }
    
    public boolean isEmpty() {

    File file = new File(FILE_PATH);

    if (!file.exists()) return true;

    try (CSVReader reader = new CSVReader(new FileReader(file))) {

        reader.readNext(); // skip header

        return reader.readNext() == null; // no data rows

    } catch (Exception e) {
        return true;
    }
}
    
}

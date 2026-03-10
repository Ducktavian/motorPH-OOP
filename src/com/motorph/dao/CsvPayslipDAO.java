

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

/**
 *
 * @author Lenovo
 */
public class CsvPayslipDAO implements PayslipDAO {
    
    private static final String FILE_PATH = "data/payslips.csv";
    
    
    public CsvPayslipDAO() {
        
    }

    public void savePayslip(Payslip payslip) {
        
        try {
            File file = new File(FILE_PATH);
            
            boolean fileExist = file.exists();
            
            CSVWriter writer = new CSVWriter(new FileWriter(file, true));
            
            // if file is new: write header
            if (!fileExist) {
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
            
            String[] row = {
                payslip.getPayslipId(),
                payslip.getEmployeeNumber(),
                payslip.getPosition(),
                payslip.getPeriodStart().toString(),
                payslip.getPeriodEnd().toString(),
                String.valueOf(payslip.getTotalHours()),
                String.valueOf(payslip.getHourlyRate()),
                String.valueOf(payslip.getGrossPay()),
                String.valueOf(payslip.getAllowanceBreakdown().getRiceSubsidy()),
                String.valueOf(payslip.getAllowanceBreakdown().getPhoneAllowance()),
                String.valueOf(payslip.getAllowanceBreakdown().getClothingAllowance()),
                String.valueOf(
                    payslip.getDeductionBreakdown() != null
                    ? payslip.getDeductionBreakdown().getSss()
                    : 0
                ),
                String.valueOf(
                    payslip.getDeductionBreakdown() != null
                    ? payslip.getDeductionBreakdown().getPhilHealth()
                    : 0
                ),
                String.valueOf(
                    payslip.getDeductionBreakdown() != null
                    ? payslip.getDeductionBreakdown().getPagIbig()
                    : 0
                ),
                String.valueOf(
                    payslip.getDeductionBreakdown() != null
                    ? payslip.getDeductionBreakdown().getWithholdingTax()
                    : 0
                ),
                String.valueOf(payslip.getNetPay())
            };
            
            writer.writeNext(row);
            writer.close();    
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Payslip> findPayslipsByEmploye(String employeeNumber) {
        
        List<Payslip> payslips = new ArrayList<>();
        
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            
            reader.readNext(); // skip header
            
            String[] row;
            
            while ((row = reader.readNext()) != null) {
                
                if (row[1].equals(employeeNumber)) {
                    
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
                    
                    /*
                       
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
                    "netPay"*/
                }
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return payslips;
    }
}

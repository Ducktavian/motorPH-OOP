package com.motorph.model;

import java.time.LocalDate;

public class Finance extends Employee {

    public Finance() {
    }

    public Finance(String employeeNumber, String lastName, String firstName, LocalDate birthday, String address, String phoneNumber, String SSSNumber, String philhealthNumber, String TIN, String pagIbigNumber, String status, String position, String immediateSupervisor, double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance) {
        super(employeeNumber, lastName, firstName, birthday, address, phoneNumber, SSSNumber, philhealthNumber, TIN, pagIbigNumber, status, position, immediateSupervisor, basicSalary, riceSubsidy, phoneAllowance, clothingAllowance);
    }

}

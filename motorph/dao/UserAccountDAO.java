
package com.motorph.dao;

import com.motorph.model.Employee;
import com.motorph.model.Role;
import com.motorph.model.UserAccount;
import com.opencsv.CSVReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;


public class UserAccountDAO {
    
    // Lists of user accounts
    private List<UserAccount> users;
    private CsvEmployeeDAO csvEmpDao = new CsvEmployeeDAO();
    private Employee employee;
    
    // Constructor
    public UserAccountDAO() {
        
        users = new ArrayList<>();
       
        try (CSVReader reader = new CSVReader(new FileReader ("user_accounts.csv"))) {
            
            String[] data;
            reader.readNext();
            

            while ((data = reader.readNext()) != null) {
                int userId = Integer.parseInt(data[0]);
                String employeeNumber = data[1].trim();
                String userName = data[2].trim();
                String passwordHash = data[3].trim();
                boolean active = Boolean.parseBoolean(data[4].trim());
                
                Role role = getRole(employeeNumber);
                
                users.add(new UserAccount(userId, employeeNumber, userName, passwordHash, role, active));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public UserAccount findByUsername(String username) {
        username = username.trim();
        for (UserAccount user: users) {
            if (user.getUsername().equals(username)) {
                System.out.println("User found!");
                return user;
            }
        }
        System.out.println("User null!");
        return null;
    }
    
    
    /////// NEW
    public void save(UserAccount user) {
        users.add(user);
    }
    
    public UserAccount findById(int id) {
        for (UserAccount user : users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }
    
    public List<UserAccount> findAll() {
        return users;
    }
    
    public Role getRole(String employeeNumber) {
        employee = csvEmpDao.findEmployee(employeeNumber);
        
        
        if (employee.getPosition().contains("hr")) {
            return Role.HR;
        }
        else if (employee.getPosition().contains("it")) {
            return Role.IT;
        }
        else if (employee.getPosition().contains("finance")){
            return Role.FINANCE;
        }
        else {
            return Role.EMPLOYEE;
        } 
        
        
    }    
}   

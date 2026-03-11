
package com.motorph.dao;

import com.motorph.model.Employee;
import com.motorph.model.Role;
import com.motorph.model.UserAccount;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;


public class CsvUserAccountDAO implements UserAccountDAO {
    
    private static final String FILE_PATH = "data/user_accounts.csv";
    private List<UserAccount> users; // Lists of user accounts
    private EmployeeDAO csvEmpDao;
    
    // Constructor
    public CsvUserAccountDAO() {
        this.users = new ArrayList<>();
        this.csvEmpDao  = new CsvEmployeeDAO();
        ensureFileExists();
        loadUsers();
        
        
    }
    
    // Handles directory and file validation
    private void ensureFileExists() {
        File file = new File(FILE_PATH);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
                String[] header = {"userId", "employeeNumber", "username", "passwordHash", "active"};
                writer.writeNext(header);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void loadUsers() {
        users.clear();
        
        try (CSVReader reader = new CSVReader(new FileReader(FILE_PATH))) {
            
            String[] data;
            reader.readNext(); // skip header
            
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
            System.err.println("Error loading user accounts: " + e.getMessage());
        }
    }
    
    @Override
    public UserAccount findByUsername(String username) {
        for (UserAccount user: users) {
            if (user.getUsername().equalsIgnoreCase(username.trim())) {
                return user;
            }
        }
        return null;
    }
    
    @Override
    public void save(UserAccount user) {
        users.add(user);
    }
    
    @Override
    public UserAccount findById(int id) {
        for (UserAccount user : users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }
    
    @Override
    public List<UserAccount> findAll() {
        return users;
    }
    
    @Override
    public Role getRole(String employeeNumber) {
        Employee employee = csvEmpDao.findEmployee(employeeNumber);
        
        if (employee == null) return Role.EMPLOYEE;
        
        String position = employee.getPosition().toLowerCase();
        
        if (position.contains("hr")) {
            return Role.HR;
        }
        else if (position.contains("it")) {
            return Role.IT;
        }
        else if (position.contains("finance")){
            return Role.FINANCE;
        }
        else {
            return Role.EMPLOYEE;
        } 
        
        
    }    
}   

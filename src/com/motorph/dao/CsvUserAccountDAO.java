
package com.motorph.dao;

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
    
    // Constructor
    public CsvUserAccountDAO() {
        this.users = new ArrayList<>();
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
                String stringRole = data[4].trim();
                boolean active = Boolean.parseBoolean(data[5].trim());
                
                Role role = getRole(stringRole.toLowerCase());
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
    public Role getRole(String stringRole) {
        if (stringRole.equals("hr")) {
            return Role.HR;
        }
        else if (stringRole.equals("it")) {
            return Role.IT;
        }
        else if (stringRole.equals("finance")){
            return Role.FINANCE;
        } else if (stringRole.equals("admin")) {
            return Role.ADMIN;
        }
        else {
            return Role.EMPLOYEE;
        } 
    }    
    
    
    
    public void update(UserAccount updatedUser) {
        List<UserAccount> allUsers = findAll();
        
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getUsername().equals(updatedUser.getUsername())) {
                allUsers.set(i, updatedUser);
                break;
            }
        }
        
        // Write the whole list back to the CSV file
        saveAll(allUsers);
    }

    private void saveAll(List<UserAccount> allUsers) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_PATH))) {
            String[] header = {"userId", "Employee #", "username", "passwordHash", "active"};


            writer.writeNext(header);

            for (UserAccount user: allUsers) {
  
                String[] row = {
                    String.valueOf(user.getUserId()),
                    user.getEmployeeNumber(),
                    user.getUsername(),
                    user.getPasswordHash(),
                    String.valueOf(user.isActive())
                };
                writer.writeNext(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    
    
}   

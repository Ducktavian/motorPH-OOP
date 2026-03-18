
package com.motorph.service;

import com.motorph.dao.UserAccountDAO;
import com.motorph.exception.UnauthorizedException;
import com.motorph.model.Role;
import com.motorph.model.UserAccount;
import com.motorph.util.Session;
import java.util.List;



public class UserManagementService {
    
    private UserAccountDAO userDAO;
    
    // :)
    public UserManagementService(UserAccountDAO userAccountDAO) {
        this.userDAO = userAccountDAO;
    }
    
    // Authorize IT
    private void authorizeIT() {
        UserAccount current = Session.getCurrentUser();
        
        if (current == null || current.getRole() != Role.IT) {
            throw new UnauthorizedException("Only IT can manage user accounts.");
        }
    }
    
    private int generateNextUserId() {
        authorizeIT();
        return userDAO.generateNextUserId();
    }
    
    public void createUser(String username, String password, Role role, String employeeNumber) {
        // Checks IT access
        authorizeIT();
        
        int newId = generateNextUserId();
        
        UserAccount newUser = new UserAccount(
                newId,
                employeeNumber,
                username,
                password,
                role,
                true   
        );
        
        userDAO.save(newUser);
    }
    
    public void updateUser(UserAccount user) {
        userDAO.update(user);
    }
    
    public void resetPassword(int userId) {
        authorizeIT();
        
        String DEFAULT_PASSWORD = "password";
        
        UserAccount user = userDAO.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found.");
        }
        user.setPasswordHash(DEFAULT_PASSWORD);
        updateUser(user);
    }
    
    public void resetPassword(int userId, String newPassword) {
        authorizeIT();
        UserAccount user = userDAO.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found.");
        }
        user.setPasswordHash(newPassword);
        updateUser(user);
    }
    
    public void changeRole(int userId, Role newRole) {
        authorizeIT();
        UserAccount user = userDAO.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setRole(newRole);
        updateUser(user);
    }
    
    public void deactivateUser(int userId) {
        authorizeIT();
        UserAccount user = userDAO.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found.");
        }
        user.setActive(false);
        updateUser(user);
    }
    
    public List<UserAccount> listUsers() {
        authorizeIT();
        return userDAO.findAll();
    }
    
    public UserAccount findByEmployeeNumber(String employeeNumber) {
        authorizeIT();
        return userDAO.findByEmployeeNumber(employeeNumber);
    }
    
    public UserAccount findByUserName(String userName) {
        authorizeIT();
        return userDAO.findByUsername(userName);
    }
    
    public UserAccount findById(int id) {
        authorizeIT();
        return userDAO.findById(id);
    }
    
}

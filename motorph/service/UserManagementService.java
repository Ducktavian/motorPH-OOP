
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
    public UserManagementService() {
        userDAO = new UserAccountDAO();
    }
    
    // Authorize IT
    private void authorizeIT() {
        UserAccount current = Session.getCurrentUser();
        
        if (current == null || current.getRole() != Role.IT) {
            throw new UnauthorizedException("Only IT can manage user accounts.");
        }
    }
    
    public void createUser(int newId, String username, String password, Role role, String employeeNumber) {
        // Checks IT access
        authorizeIT();
        
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
    
    public void resetPassword(int userId, String newPassword) {
        
        authorizeIT();
        
        UserAccount user = userDAO.findById(userId);
        
        if (user == null) {
            throw new RuntimeException("User not found.");
        }
        
        user.setPasswordHash(newPassword);
    }
    
    public void changeRole(int userId, Role newRole) {
        
        authorizeIT();
        
        UserAccount user = userDAO.findById(userId);
        
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        
        user.setRole(newRole);
        
    }
    
    public void deactivateUser(int userId) {
        
        authorizeIT();
        
        UserAccount user = userDAO.findById(userId);
        
        if (user == null) {
            throw new RuntimeException("User not found.");
        }
        
        user.setActive(false);
    }
    
    public List<UserAccount> listUsers() {
        authorizeIT();
        return userDAO.findAll();
    }
    
}

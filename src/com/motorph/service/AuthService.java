
package com.motorph.service;

import com.motorph.dao.UserAccountDAO;
import com.motorph.model.UserAccount;


public class AuthService {
    
    private UserAccountDAO userAccountDAO;
    
    public AuthService() {
        userAccountDAO = new UserAccountDAO();
    }
    
    public UserAccount login(String username, String password) throws Exception {
        
        UserAccount user = userAccountDAO.findByUsername(username);
        
        if (user == null) {
            throw new Exception("User not found.");
        }
        
        if (!user.getPasswordHash().equals(password)) {
            throw new Exception("Invalid password.");
        }
        
        return user;
    }
        
    
 
}

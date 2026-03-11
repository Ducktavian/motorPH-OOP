
package com.motorph.service;

import com.motorph.dao.CsvUserAccountDAO;
import com.motorph.model.UserAccount;
import com.motorph.util.PasswordUtil;


public class AuthService {
    
    private CsvUserAccountDAO userAccountDAO;
    
    public AuthService() {
        userAccountDAO = new CsvUserAccountDAO();
    }
    
    public UserAccount login(String username, String password) throws Exception {
        
        UserAccount user = userAccountDAO.findByUsername(username);
        
        if (user == null) {
            System.out.println("User not found.");
            throw new Exception("User not found.");
        }
        
        // Checks passowrd
        if (!PasswordUtil.verifyPassword(password, user.getPasswordHash())) {
            System.out.println("Invalid password.");
            throw new Exception("Invalid password.");
        }
        
        return user;
    }
        
    
 
}

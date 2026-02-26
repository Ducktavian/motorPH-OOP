/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.dao;

import com.motorph.model.Role;
import com.motorph.model.UserAccount;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class UserAccountDAO {
    
    private List<UserAccount> users;
    
    public UserAccountDAO() {
        
        users = new ArrayList<>();
        
        users.add(new UserAccount(1, "hr_admin", "1234", Role.HR, "1001", true));
        users.add(new UserAccount(2, "finance_admin", "1234", Role.FINANCE, "1002", true));
        users.add(new UserAccount(3, "it_admin", "1234", Role.IT, "1003", true));
        users.add(new UserAccount(4, "emp1", "1234", Role.EMPLOYEE, "1004", true));
    }
    
    public UserAccount findByUsername(String username) {
        for (UserAccount user: users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
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
    
    
}   

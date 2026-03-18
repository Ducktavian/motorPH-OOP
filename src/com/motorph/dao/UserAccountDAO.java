/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.dao;

import com.motorph.model.Role;
import com.motorph.model.UserAccount;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface UserAccountDAO {
    UserAccount findByUsername(String username);
    UserAccount findById(int id);
    List<UserAccount> findAll();
    int generateNextUserId();
    
    
    void save(UserAccount user);
    void update(UserAccount updatedUser);
    Role getRole(String employeeNumber);
    UserAccount findByEmployeeNumber(String employeeNumber);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorph.util;

import com.motorph.model.UserAccount;

/**
 *
 * @author Lenovo
 */
public class Session {
    
    private static UserAccount currentUser;
    
    private Session() {
        // prevent instantiation
    }
    
    public static void setCurrentUser(UserAccount user) {
       currentUser = user;
    }
    
    public static UserAccount getCurrentUser() {
        return currentUser;
    }
    
    public static void clear() {
        currentUser = null;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorph.model;

import java.time.LocalDate;

/**
 *
 * @author Lenovo
 */
interface Requestable {
    
    String getRequestId();
    
    String getEmployeeId();
    
    LocalDate dateFiled();
    
    RequestStatus getStatus();
    
    void approve();
    
    void reject();
}

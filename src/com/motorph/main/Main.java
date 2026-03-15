
package com.motorph.main;


import com.motorph.ui.LoginUI;


//

public class Main {
    
    public static void main(String[] args) {
    
        
        // Use invokeLater to ensure thread safety for Swing components
        java.awt.EventQueue.invokeLater(() -> {
            LoginUI loginFrame = new LoginUI();
            loginFrame.setLocationRelativeTo(null); // Centers the window on screen
            loginFrame.setVisible(true);
            
                    
        });  
        
        int action;
        
        while (true) {
            
        }
        
    }

    private void timeIn() {
        
    }
    
    private void timeOut() {
        
    }
    
    
    
}
        
     
    
   
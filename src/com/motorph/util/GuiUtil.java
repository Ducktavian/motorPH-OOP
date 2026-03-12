package com.motorph.util;

import javax.swing.JTextField;


public class GuiUtil {
    public static double getDoubleFromField(JTextField field) {
        String text = field.getText().trim();
        return (text.isEmpty()) ? 0.0 : Double.parseDouble(text.replace(",", ""));
    }
    
    public static void openFrame(javax.swing.JFrame oldFrame, javax.swing.JFrame newFrame) {
        oldFrame.dispose();
        newFrame.setVisible(true);
    }
}

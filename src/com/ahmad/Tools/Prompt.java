package com.ahmad.Tools;

import javax.swing.*;

public class Prompt {
    public static void displayErrorMessage(String errorMessage) {
        JFrame jFrame = new JFrame("Input Error");
        JLabel jLabel = new JLabel(errorMessage);

        jFrame.add(jLabel);
        jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}

package com.ahmad;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Views.ModeOne.MainViewModeOne;

import javax.swing.*;

public class Startup {
    private JFrame jFrame;

    public Startup() {
        MainViewModeOne mainViewModeOne = new MainViewModeOne(this, new SystemModelModeOne());

        jFrame = new JFrame("Coupled Motion Simulator");
        jFrame.getContentPane().add(mainViewModeOne.mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setMinimumSize(jFrame.getSize());
        jFrame.setVisible(true);
    }

    public JFrame getJFrame() {
        return jFrame;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Startup();
    }
}

// round acceleration and velocity values
// remove hardcoded values
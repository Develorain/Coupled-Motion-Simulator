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

// TODO: < QUALITY OF LIFE >
// make window resizable (support for simulation area resizing, possibly scale when window is resized)
// Window minimum size is a little too small on linux
// round acceleration and velocity values
// remove slopemodels classes, wires, etc
// puting 0f as a number doesn't give error
// remove hardcoded values

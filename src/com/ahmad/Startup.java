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

// TODO: < REAL TODO >
// Remove mode two input type two

// TODO: < DATA VALIDATION >
// acceleration can not be greater than 9.8 or less than -9.8, mu can't be negative, angle must be 0 <= angle <= 90
// user can input negative angles and angles above 90 degrees
// add data validation when a number is input (for example if a letter is put or if the whole value is erased)
// putting in no mass breaks the program (for example, removing all the value of dangling box and pressing start), make an error occur

// TODO: < QUALITY OF LIFE >
// make window resizable (support for simulation area resizing, possibly scale when window is resized)
// round acceleration and velocity values
// remove slopemodels classes, wires, etc
// Window minimum size is a little too small on linux

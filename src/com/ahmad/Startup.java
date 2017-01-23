package com.ahmad;

/** Startup
 * The entry point for the program
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Views.ModeOne.MainViewModeOne;

import javax.swing.*;

public class Startup {
    private JFrame jFrame;   // Declare the JFrame reference variable

    /** Default Constructor */
    public Startup() {
        MainViewModeOne mainViewModeOne = new MainViewModeOne(this, new SystemModelModeOne());

        jFrame = new JFrame("Coupled Motion Simulator");
        jFrame.getContentPane().add(mainViewModeOne.getMainPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setMinimumSize(jFrame.getSize());
        jFrame.setVisible(true);
    }

    /** Program entry point
     * @param args the arguments passed into the program */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Startup();
    }

    /** Returns the JFrame
     * @return the JFrame window */
    public JFrame getJFrame() {
        return jFrame;
    }
}

// make view private
// round acceleration and velocity values
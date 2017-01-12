package com.ahmad;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Views.ModeOne.MainViewModeOne;

import javax.swing.*;
import java.awt.*;

public class Startup {
    public static JFrame jFrame;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        MainViewModeOne mainViewModeOne = new MainViewModeOne(new SystemModelModeOne());

        jFrame = new JFrame("Coupled Motion Simulator");
        jFrame.setMinimumSize(new Dimension(1250, 850));
        //jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.getContentPane().add(mainViewModeOne.mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        //System.out.println(mainViewModeTwo.systemView.systemPanel.getSize());
        //System.out.println(jFrame.getSize());
    }
}

// TODO: < REAL TODO >
// TODO: Implement mode two physics
// TODO: Variable switching for mode two

// TODO: < BUGS >
// for some reason the program just crashes after simulating for around 10 seconds
// simulation view can not be less than 700 pixels wide without the boxes not being centered
// borders should scale with window size, not 100 pixels absolute
// acceleration can not be greater than 9.8 or less than -9.8, mu can't be negative, angle must be 0 <= angle <= 90

// TODO: < TEST INPUTS >
// TODO: when the test input when using (Solve for Friction, Acceleration, and Tension) is 56 for left mass, 100 for right mass, 1000 for mu, and 25 for angle, the velocity is -0.0
// user can input negative angles and angles above 90 degrees // limit angle to 89 // todo
// if friction is neg when accel is neg, give error // todo
// if friction is pos when accel is pos, give error // todo

// TODO: < QUALITY OF LIFE >
// make sure the boxes do not go past the pulley or off the screen
// make window resizable (support for simulation area resizing, possibly scale when window is resized)
// pressing the start button multiple times makes the boxes move faster. this happens because multiple timers are created, each of which are adding to velocity (solution, just disable all text fields when simulation starts)
// add data validation when a number is input (for example if a letter is put or if the whole value is erased)
// putting in no mass breaks the program (for example, removing all the value of dangling box and pressing start), make an error occur
// show how long simulation has been running for (time)
// if simulation is active, and you select the angle text field, and press anything (ex. shift), the boxes move the their original position (solution, just disable all text fields when simulation starts)

// TODO: < ALMOST USELESS >
// round acceleration and velocity values
// for mode one, draw a line from the end of the first slope to the bottom of the screen to show that there's a wall below the pulley
// find a fix for jagged edges on sloped box (probably no fix)

// if the friction is too great the box moves backwards. instead, the box should not move at all if frictional force is greater than gravity force (pretty sure this is out of date)
// make sure the boxes don't start at the polar extremes of the slope
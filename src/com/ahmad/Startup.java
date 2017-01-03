package com.ahmad;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Views.ModeOne.MainViewModeOne;

import javax.swing.*;

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
        //jFrame.setMinimumSize(new Dimension(708, 851));
        //jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.getContentPane().add(mainViewModeOne.mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        //System.out.println(mainViewModeTwo.systemView.systemPanel.getSize());
        System.out.println(jFrame.getSize());
    }
}

// TODO: ** REAL TODO **
// TODO: Variable switching (mode two)
// TODO: Draw pulleys and wires


// TODO: **BUGS**
// TODO: IMPORTANT: BoxModel needs to take both mode1 and mode2 system models
// start button doesn't actually set the new values of the boxes (mass)
// pressing the start button multiple times makes the boxes move faster. this happens because multiple timers are created, each of which are adding to velocity
// putting in no mass breaks the program (for example, removing all the value of dangling box and pressing start)
// user can input negative angles and angles above 90 degrees
// if the friction is too great the box moves backwards. instead, the box should not move at all if frictional force is greater than gravity force
// make sure the boxes don't start at the polar extremes of the slope and make sure it does not go past the pulley or past the slope
// if simulation is active, and you select the angle text field, and press anything (ex. shift), the boxes move the their original position
// simulation view can not be less than 700 pixels wide without the boxes not being centered
// borders should scale with window size, not 100 pixels absolute

// TODO: **IMPORTANT**
// based on what value is not provided (either mass, mu, or acceleration), solve for not given value
// draw slope, pulley, and string
// the positions of the boxes should not be hard-coded, but rather be based on the current size of the simulation view
// add multiple modes
// fix acceleration calculation   (complete)
// add tension calculation        (complete)

//if friction is 0, mu is 0. vice versa // todo
//limit angle to 89 // todo
//if friction is neg when accel is neg, give error // todo
//if friction is pos when accel is pos, give error // todo SEE INTENSE PHYSICS FILE

// TODO: **SECONDARY**
// make it so that you can either pick to put in mu value, or just the frictional force of the box
// make window resizable (support for simulation area resizing, possibly scale when window is resized)
// add data validation when a number is input (for example if a letter is put or if the whole value is erased)
// round acceleration and velocity values
// find a fix for jagged edges on sloped box
// make a JLabel inside the box that follows the box and has the letter "A" on it
// show how long simulation has been running for (time)
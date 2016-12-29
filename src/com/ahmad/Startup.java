package com.ahmad;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Views.ModeOne.MainViewModeOne;

import javax.swing.*;

public class Startup {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SystemModelModeOne systemModelModeOne = new SystemModelModeOne();
        MainViewModeOne mainViewModeOne = new MainViewModeOne(systemModelModeOne);

        JFrame jFrame = new JFrame("Coupled Motion Simulator");
        //jFrame.setMinimumSize(new Dimension(708, 851));
        //jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setContentPane(mainViewModeOne.mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        //System.out.println(mainViewModeTwo.systemView.systemPanel.getSize());
        System.out.println(jFrame.getSize());
    }
}

// TODO: **BUGS**
// TODO: IMPORTANT: boxmodel needs to take both mode1 and mode2 system models
// start button doesn't actually set the new values of the boxes (mass)
// pressing the start button multiple times makes the boxes move faster. this happens because multiple timers are created, each of which are adding to velocity
// putting in no mass breaks the program (for example, removing all the value of dangling box and pressing start)
// user can input negative angles and angles above 90 degrees
// if the friction is too great the box moves backwards. instead, the box should not move at all if frictional force is greater than gravity force
// make sure the boxes don't start at the polar extremes of the slope and make sure it does not go past the pulley or past the slope
// timer in StartButtonController only takes integers when it should take fractions
// if simulation is active, and you select the angle text field, and press anything (ex. shift), the boxes move the their original position
// simulation view can not be less than 700 pixels wide without the boxes not being centered
// calculating velocity and position and iterating 60 times a second is incorrect because the acceleration is instantaneous, so the velocity is accelerating in between the 1/60'ths of a second


// TODO: **IMPORTANT**
// based on what value is not provided (either mass, mu, or acceleration), solve for not given value
// draw slope, pulley, and string
// the positions of the boxes should not be hard-coded, but rather be based on the current size of the simulation view
// add multiple modes
// fix acceleration calculation   (complete)
// add tension calculation        (complete)
// Each controller doesn't need to be an actionlistener, Can have one controller per view that has multiple actionlisteners


// TODO: **SECONDARY**
// make it so that you can either pick to put in mu value, or just the frictional force of the box
// make window resizable (support for simulation area resizing, possibly scale when window is resized)
// add data validation when a number is input (for example if a letter is put or if the whole value is erased)
// round acceleration and velocity values
// find a fix for jagged edges on sloped box
// make a JLabel inside the box that follows the box and has the letter "A" on it
// show how long simulation has been running for (time)

/**
 * 1. Start at the center
 * 2. Draw the slope at the angle
 * 3. Go down the slope until you are at the end of the screen and draw block A there, 100 offset
 * 4. Go up the slope until you're out of screen and that will be your cutoff, 100 offset
 */
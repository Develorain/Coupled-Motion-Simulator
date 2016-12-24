package com.ahmad;

import com.ahmad.Models.ModeOne.SystemModel;
import com.ahmad.Views.MainView;

import javax.swing.*;
import java.awt.*;

public class Startup {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SystemModel systemModel = new SystemModel();
        MainView mainView = new MainView(systemModel);

        JFrame jFrame = new JFrame("Coupled Motion Simulator");
        jFrame.setMinimumSize(new Dimension(800, 600));
        //jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setContentPane(mainView.mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

        //System.out.println(mainView.systemView.systemPanel.getSize());
        System.out.println(jFrame.getSize());
    }
}

// TODO: **BUGS**
// pressing the start button multiple times makes the boxes move faster. this happens because multiple timers are created, each of which are adding to velocity
// putting in no mass breaks the program (for example, removing all the value of dangling box and pressing start)
// user can input negative angles and angles above 90 degrees
// if the friction is too great the box moves backwards. instead, the box should not move at all if frictional force is greater than gravity force
// make sure the boxes don't start at the polar extremes of the slope and make sure it does not go past the pulley or past the slope
// timer in StartButtonController only takes integers when it should take fractions


// TODO: **IMPORTANT**
// based on what value is not provided (either mass, mu, or acceleration), solve for not given value
// draw slope, pulley, and string
// make it so that you can either pick to put in mu value, or just the frictional force of the box
// the positions of the boxes should not be hard-coded, but rather be based on the current size of the simulation view
// fix acceleration calculation   (complete)
// add tension calculation        (complete)


// TODO: **SECONDARY**
// make window resizable (support for simulation area resizing, possibly scale when window is resized)
// add data validation when a number is input (for example if a letter is put or if the whole value is erased)
// round acceleration and velocity values
// find a fix for jagged edges on sloped box
// make a JLabel inside the box that follows the box and has the letter "A" on it

/**
 * 1. Start at the center
 * 2. Draw the slope at the angle
 * 3. Go down the slope until you are at the end of the screen and draw block A there, 100 offset
 * 4. Go up the slope until you're out of screen and that will be your cutoff, 100 offset
 */
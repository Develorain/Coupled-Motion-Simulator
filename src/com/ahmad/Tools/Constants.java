package com.ahmad.Tools;

/** Constants
 * Stores global constants
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import java.awt.*;

public class Constants {
    public static final double GRAVITY = 9.8;         // Declare the gravity constant in meters per second squared
    public static final int UPDATES_PER_SECOND = 100; // Declare the updates per second constant
    public static final int SIMULATION_WIDTH_PIXELS = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth() * 0.9);   // Declare the simulation width constant in pixels
    public static final int SIMULATION_HEIGHT_PIXELS = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight() * 0.7); // Declare the simulation height constant in pixels
}
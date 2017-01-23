package com.ahmad.Models;

/** SlopeModel
 * Performs the calculations for the slope in system
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Tools.Vector;

public abstract class SlopeModel {
    protected Vector leftCoord;   // Declare the left coordinate of the slope
    protected Vector rightCoord;  // Declare the right coordinate of the slope

    /** Default Constructor */
    public SlopeModel() {
        leftCoord = Vector.createFromCartesian(0, 0);   // Initialize the left coordinate of the slope
        rightCoord = Vector.createFromCartesian(0, 0);  // Initialize the right coordinate of the slope
    }

    /** Calculates the coordinates of the slope based on the angle */
    protected abstract void calculateCoordinates();

    /** Returns the left coordinate of the slope
     * @return the left coordinate of the slope */
    public Vector getLeftCoord() {
        return leftCoord;
    }

    /** Returns the right coordinate of the slope
     * @return the right coordinate of the slope */
    public Vector getRightCoord() {
        return rightCoord;
    }
}

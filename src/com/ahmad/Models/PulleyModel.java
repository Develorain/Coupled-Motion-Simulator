package com.ahmad.Models;

/** PulleyModel
 * Performs the calculations for the pulley in system
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Tools.Vector;

public class PulleyModel {
    private final Vector topLeftCorner;     // Declare the top left corner of the pulley
    private final int radius;               // Declare the radius of the pulley

    /** Default Constructor
     * @param radius  the radius of the pulley in pixels
     * @param x       the x coordinate of the pulley
     * @param y       the y coordinate of the pulley */
    public PulleyModel(int radius, double x, double y) {
        this.radius = radius;
        this.topLeftCorner = Vector.createFromCartesian(x, y);
    }

    /** Returns the radius of the pulley
     * @return the radius of the pulley */
    public double getRadius() {
        return radius;
    }

    /** Returns the diameter of the pulley
     * @return the diameter of the pulley */
    public double getDiameter() {
        return radius * 2;
    }

    /** Returns the top left corner of the pulley
     * @return the top left corner of the pulley */
    public Vector getTopLeftCorner() {
        return topLeftCorner;
    }
}

package com.ahmad.Tools;

/** Vector
 * Stores position and direction with support for two coordinate systems
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

public class Vector {
    private double x;   // Declare the x variable
    private double y;   // Declare the y variable

    /** Creates a vector based on the cartesian plane
     * @param x the x coordinate on the cartesian plane
     * @param y the y coordinate on the cartesian plane
     * @return a new vector instance from the given coordinates */
    public static Vector createFromCartesian(double x, double y) {
        Vector vector = new Vector();
        vector.setX(x);
        vector.setY(y);

        return vector;
    }

    /** Creates a vector based on the polar coordinate system
     * @param r     the radius on the polar coordinate system
     * @param theta the angle, in degrees, on the polar coordinate system
     * @return a new vector instance from the given coordinates */
    public static Vector createFromPolar(double r, double theta) {
        Vector vector = new Vector();
        vector.setRAndTheta(r, theta);

        return vector;
    }

    /** Sets the radius and theta of the vector
     * @param r     the radius on the polar coordinate system
     * @param theta the angle, in degrees, on the polar coordinate system */
    public void setRAndTheta(double r, double theta) {
        x = r * MathTools.cos(theta);
        y = r * MathTools.sin(theta);
    }

    /** Returns the radius in the polar coordinate system
     * @return the radius in the polar coordinate system */
    public double getR() {
        return Math.sqrt(x * x + y * y);
    }

    /** Returns the x coordinate on the cartesian plane
     * @return the x coordinate on the cartesian plane */
    public double getX() {
        return x;
    }

    /** Sets the x coordinate on the cartesian plane
     * @param x the new x coordinate of the vector */
    public void setX(double x) {
        this.x = x;
    }

    /** Returns the y coordinate on the cartesian plane
     * @return the y coordinate on the cartesian plane */
    public double getY() {
        return y;
    }

    /** Sets the y coordinate on the cartesian plane
     * @param y the new y coordinate of the vector */
    public void setY(double y) {
        this.y = y;
    }
}

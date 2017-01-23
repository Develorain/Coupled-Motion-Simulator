package com.ahmad.Models;

/** BoxModel
 * Performs the calculations for the box in system
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public abstract class BoxModel {
    protected final int BOX_WIDTH = 100;    // Declare the box's width
    protected final int BOX_HEIGHT = 100;   // Declare the box's height

    protected Vector topLeftCorner;         // Declare the coordinate of the box's top left corner
    protected Vector topRightCorner;        // Declare the coordinate of the box's top right corner
    protected Vector bottomLeftCorner;      // Declare the coordinate of the box's bottom left corner
    protected Vector bottomRightCorner;     // Declare the coordinate of the box's bottom right corner

    protected double mass;         // Declare the box's mass in kilograms
    protected double mu;           // Declare the box's mu value
    protected double friction;     // Declare the box's frictional force in newtons
    protected double gravForce;    // Declare the x component of the gravitational force on the box

    private Vector velocity;       // Declare the velocity vector of the box
    private Vector acceleration;   // Declare the acceleration vector of the box

    /** Default Constructor
     * @param mass   the mass of the box
     * @param mu     the mu value of the box */
    public BoxModel(double mass, double mu) {
        this.mass = mass;
        this.mu = mu;

        velocity = new Vector();

        // Set default value for each coordinate to 0, 0
        topLeftCorner = Vector.createFromCartesian(0, 0);
        topRightCorner = Vector.createFromCartesian(0, 0);
        bottomLeftCorner = Vector.createFromCartesian(0, 0);
        bottomRightCorner = Vector.createFromCartesian(0, 0);
    }

    /** Calculates and updates the new velocity of the box based on the time passed
     * @param elapsedSeconds represents the number of time in seconds that have passed since the last call to this method occured */
    public void updateVelocity(double elapsedSeconds) {
        velocity.setX(acceleration.getX() * elapsedSeconds);
        velocity.setY(acceleration.getY() * elapsedSeconds);
    }

    /** Calculates and updates the new position of the box based on the time that has passed
     * @param elapsedSeconds represents the number of seconds that have passed since the last call to this method occured */
    public void updatePosition(double elapsedSeconds) {
        topLeftCorner.setX(topLeftCorner.getX() + (0.5 * acceleration.getX() * elapsedSeconds * elapsedSeconds));
        topLeftCorner.setY(topLeftCorner.getY() - (0.5 * acceleration.getY() * elapsedSeconds * elapsedSeconds));

        calculateBoxVerticesFromTopLeft();
    }

    /** Calculates and updates the gravitational force on the box in newtons based on the angle
     * @param slopeAngle the angle of the slope the box is on in degrees */
    public void updateGravForce(double slopeAngle) {
        gravForce = mass * Constants.GRAVITY * MathTools.sin(slopeAngle);
    }

    /** Calculates the starting position of the box based on the slope it is on */
    public abstract void calculateStartingPositionCoordinates();

    /** Calculates the top right, bottom left, and bottom right vertices based on the top left vertex coordinate */
    public abstract void calculateBoxVerticesFromTopLeft();

    /** Sets the acceleration of the box to the value provided
      * @param acceleration the new box's acceleration */
    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    /** Returns the mass of the box
      * @return the mass of the box in kilograms */
    public double getMass() {
        return mass;
    }

    /** Sets the mass of the box to the value provided
      * @param mass the new mass of the box in kilograms */
    public void setMass(double mass) {
        this.mass = mass;
    }

    /** Returns the mu value of the box
      * @return the mu value of the box */
    public double getMu() {
        return mu;
    }

    /** Sets the mu value of the box to the value provided
      * @param mu this new mu value of the box */
    public void setMu(double mu) {
        this.mu = mu;
    }

    /** Returns the friction value
      * @return the friction value of the box */
    public double getFriction() {
        return friction;
    }

    /** Returns the box width in pixels
     * @return the width of the box in pixels */
    public int getBoxWidth() {
        return BOX_WIDTH;
    }

    /** Returns the box height in pixels
     * @return the height of the box in pixels */
    public int getBoxHeight() {
        return BOX_HEIGHT;
    }

    /** Returns the gravitational force on the box
     * @return the force of gravity on the box */
    public double getGravForce() {
        return gravForce;
    }

    /** Returns the velocity of the box
     * @return the velocity of the box in meters per second */
    public Vector getVelocity() {
        return velocity;
    }

    /** Returns the top left corner of the box
     * @return the top left corner of the box */
    public Vector getTopLeftCorner() {
        return topLeftCorner;
    }

    /** Returns the top right corner of the box
     * @return the top right corner of the box */
    public Vector getTopRightCorner() {
        return topRightCorner;
    }

    /** Returns the bottom left corner of the box
     * @return the bottom left corner of the box */
    public Vector getBottomLeftCorner() {
        return bottomLeftCorner;
    }

    /** Returns the bottom right corner of the box
     * @return the bottom right corner of the box */
    public Vector getBottomRightCorner() {
        return bottomRightCorner;
    }
}
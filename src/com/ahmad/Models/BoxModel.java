package com.ahmad.Models;

import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public abstract class BoxModel {
    public Vector topLeftCorner = Vector.createFromCartesian(0, 0);
    public Vector topRightCorner = Vector.createFromCartesian(0, 0);
    public Vector bottomLeftCorner = Vector.createFromCartesian(0, 0);
    public Vector bottomRightCorner = Vector.createFromCartesian(0, 0);

    protected final int boxWidth = 100;
    protected final int boxHeight = 100;
    public double mass;
    public double xComponentOfGravitationalForce;    // The x component of the gravitational force on the box

    public Vector velocity = new Vector();
    public Vector acceleration;

    public BoxModel(double mass) {
        this.mass = mass;
    }

    public void updateVelocity(double elapsedSeconds) {
        velocity.setX(acceleration.getX() * elapsedSeconds);
        velocity.setY(acceleration.getY() * elapsedSeconds);
    }

    public void updatePosition(double elapsedSeconds) {
        topLeftCorner.setX(topLeftCorner.getX() + (0.5 * acceleration.getX() * elapsedSeconds * elapsedSeconds));
        topLeftCorner.setY(topLeftCorner.getY() - (0.5 * acceleration.getY() * elapsedSeconds * elapsedSeconds));

        calculateBoxVerticesFromTopLeft();
    }

    public void updateXComponentOfGravitationalForce(double slopeAngle) {
        xComponentOfGravitationalForce = mass * Constants.GRAVITY * MathTools.sin(slopeAngle);
    }

    public abstract void calculateStartingPositionCoordinates();

    public abstract void calculateBoxVerticesFromTopLeft();

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public int getBoxWidth() {
        return boxWidth;
    }

    public int getBoxHeight() {
        return boxHeight;
    }
}
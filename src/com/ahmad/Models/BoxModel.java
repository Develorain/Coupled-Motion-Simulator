package com.ahmad.Models;

import com.ahmad.Tools.Vector;

public abstract class BoxModel {
    public Vector topLeftCorner;
    public Vector topRightCorner;
    public Vector bottomLeftCorner;
    public Vector bottomRightCorner;

    protected double x;  // making this double makes drawing code a lot bulkier since i need to convert from double
    protected double y;  // but keeping it as int doesn't allow me to increment the position
    protected final int boxWidth = 100;
    protected final int boxHeight = 100;
    public double mass;

    public Vector velocity = new Vector();
    public Vector acceleration;

    public BoxModel(double mass) {
        this.mass = mass;
    }

    public void updatePosition(double elapsedSeconds) {
        topLeftCorner.setX(topLeftCorner.getX() + (0.5 * acceleration.getX() * elapsedSeconds * elapsedSeconds));
        topLeftCorner.setY(topLeftCorner.getY() - (0.5 * acceleration.getY() * elapsedSeconds * elapsedSeconds));
    }

    public void updateVelocity(double elapsedSeconds) {
        velocity.setX(acceleration.getX() * elapsedSeconds);
        velocity.setY(acceleration.getY() * elapsedSeconds);
    }

    // need calculate corners of box based on the coordinates of the topleft corner coordinates method

    public abstract void calculateStartingPositionCoordinates();

    public double getMass() {
        return mass;
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getBoxWidth() {
        return boxWidth;
    }

    public int getBoxHeight() {
        return boxHeight;
    }
}
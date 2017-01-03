package com.ahmad.Models;

import com.ahmad.Tools.Vector;

public abstract class BoxModel {
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
        x = x + (0.5 * acceleration.getX() * elapsedSeconds * elapsedSeconds);
        y = y - (0.5 * acceleration.getY() * elapsedSeconds * elapsedSeconds);
    }

    public void updateVelocity(double elapsedSeconds) {
        velocity.setX(acceleration.getX() * elapsedSeconds);
        velocity.setY(acceleration.getY() * elapsedSeconds);
    }

    public abstract void calculateCoordinates();

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
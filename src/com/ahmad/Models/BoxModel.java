package com.ahmad.Models;

import com.ahmad.Tools.Vector;

public abstract class BoxModel {
    public Vector topLeftCorner = Vector.createFromCartesian(0, 0);
    public Vector topRightCorner = Vector.createFromCartesian(0, 0);
    public Vector bottomLeftCorner = Vector.createFromCartesian(0, 0);
    public Vector bottomRightCorner = Vector.createFromCartesian(0, 0);

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

        calculateBoxVerticesFromTopLeft();
    }

    public void updateVelocity(double elapsedSeconds) {
        velocity.setX(acceleration.getX() * elapsedSeconds);
        velocity.setY(acceleration.getY() * elapsedSeconds);
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public abstract void calculateStartingPositionCoordinates();

    public abstract void calculateBoxVerticesFromTopLeft();

    public double getMass() {
        return mass;
    }

    public int getBoxWidth() {
        return boxWidth;
    }

    public int getBoxHeight() {
        return boxHeight;
    }
}
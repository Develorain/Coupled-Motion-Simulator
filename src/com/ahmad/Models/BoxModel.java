package com.ahmad.Models;

import com.ahmad.Models.ModeOne.SystemModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.Vector;

public abstract class BoxModel {
    protected SystemModel systemModel;

    protected double x;  // making this double makes drawing code a lot bulkier since i need to convert from double
    protected double y;  // but keeping it as int doesn't allow me to increment the position
    protected final int boxWidth = 100;
    protected final int boxHeight = 100;
    private double mass;

    private Vector velocity = new Vector();
    private Vector acceleration;

    public BoxModel(SystemModel systemModel, double mass) {
        this.systemModel = systemModel;
        this.mass = mass;

        calculateCoordinates();
    }

    public void updatePosition() {
        x += velocity.getX() * Constants.DELTA_TIME_SECONDS;
        y -= velocity.getY() * Constants.DELTA_TIME_SECONDS;
    }

    public void updateVelocity() {
        velocity.setX(velocity.getX() + acceleration.getX() * Constants.DELTA_TIME_SECONDS);
        velocity.setY(velocity.getY() + acceleration.getY() * Constants.DELTA_TIME_SECONDS);
    }

    public abstract void calculateCoordinates();

    public double getMass() {
        return mass;
    }

    public double getVelocityMagnitude() {
        return velocity.getR();
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
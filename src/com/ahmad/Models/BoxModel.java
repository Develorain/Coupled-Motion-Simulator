package com.ahmad.Models;

import com.ahmad.Models.ModeOne.SystemModel;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public abstract class BoxModel {
    protected SystemModel systemModel;

    protected double x;  // making this double makes drawing code a lot bulkier since i need to convert from double
    protected double y;  // but keeping it as int doesn't allow me to increment the position
    private final int width = 100;
    private final int height = 100;
    private double mass;

    private Vector velocity = new Vector();
    private Vector acceleration;

    public BoxModel(SystemModel systemModel, double mass) {
        this.systemModel = systemModel;
        this.mass = mass;

        calculateCoordinates();
    }

    public void updatePosition() {
        x += velocity.getX() * MathTools.DELTA_TIME_SECONDS;
        y -= velocity.getY() * MathTools.DELTA_TIME_SECONDS;
    }

    public void updateVelocity() {
        velocity.setX(velocity.getX() + acceleration.getX() * MathTools.DELTA_TIME_SECONDS);
        velocity.setY(velocity.getY() + acceleration.getY() * MathTools.DELTA_TIME_SECONDS);
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
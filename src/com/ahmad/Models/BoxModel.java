package com.ahmad.Models;

import com.ahmad.Models.ModeOne.SystemModel;
import com.ahmad.Tools.Vector;

public abstract class BoxModel {
    private static final double DELTA_TIME = 1.0 / 60.0;

    protected SystemModel systemModel;

    protected double x;  // making this double makes drawing code a lot bulkier since i need to convert from double
    protected double y;  // but keeping it as int doesn't allow me to increment the position
    protected final int width = 100;
    protected final int height = 100;
    protected double mass;
    protected double mu;

    private Vector velocity = new Vector();
    private Vector acceleration;

    public BoxModel(SystemModel systemModel, double mass, double mu) {
        this.systemModel = systemModel;
        this.mass = mass;
        this.mu = mu;

        setPositionBasedOnAngle();
    }

    public void updatePosition() {
        x += velocity.getX() * DELTA_TIME;
        y -= velocity.getY() * DELTA_TIME;
    }

    public void updateVelocity() {
        velocity.setX(velocity.getX() + acceleration.getX() * DELTA_TIME);
        velocity.setY(velocity.getY() + acceleration.getY() * DELTA_TIME);
    }

    public abstract void setPositionBasedOnAngle();

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
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
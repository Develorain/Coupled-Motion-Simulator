package com.ahmad.Models;

import com.ahmad.Tools.Vector;

public abstract class PulleyModel {
    public Vector topLeftCorner = Vector.createFromCartesian(0, 0);

    private int radius;

    public abstract void calculateCoordinates();

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return radius * 2;
    }
}

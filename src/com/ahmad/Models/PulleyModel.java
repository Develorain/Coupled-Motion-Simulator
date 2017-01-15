package com.ahmad.Models;

import com.ahmad.Tools.Vector;

public class PulleyModel {
    private final Vector topLeftCorner;
    private final int radius;

    public PulleyModel(int radius, double x, double y) {
        this.radius = radius;
        this.topLeftCorner = Vector.createFromCartesian(x, y);
    }

    public double getRadius() {
        return radius;
    }

    public double getDiameter() {
        return radius * 2;
    }

    public Vector getTopLeftCorner() {
        return topLeftCorner;
    }
}

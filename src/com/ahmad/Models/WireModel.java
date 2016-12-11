package com.ahmad.Models;

public class WireModel {
    private BoxModel box;
    public double tension;

    public WireModel(BoxModel box) {
        this.box = box;
    }

    public void calculateTension(double acceleration) {
        tension = box.getMass() * 9.8 - box.getMass() * acceleration;
    }
}

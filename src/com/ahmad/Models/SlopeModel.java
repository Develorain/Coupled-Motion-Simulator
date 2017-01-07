package com.ahmad.Models;

public abstract class SlopeModel {
    public int x1;  // left x
    public int y1;  // left y
    public int x2;  // right x
    public int y2;  // right y

    public abstract void calculateCoordinates();
}

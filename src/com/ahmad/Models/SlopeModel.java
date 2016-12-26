package com.ahmad.Models;

public abstract class SlopeModel {
    public int x1;  // bottom left x
    public int y1;  // bottom left y
    public int x2;  // top right x
    public int y2;  // top right y

    public abstract void calculateCoordinates();
}

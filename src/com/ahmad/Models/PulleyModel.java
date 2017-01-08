package com.ahmad.Models;

import com.ahmad.Tools.Vector;

public abstract class PulleyModel {
    public Vector topLeftCorner = Vector.createFromCartesian(0, 0);

    public int radius;
    public int diameter;

    public abstract void calculateCoordinates();
}

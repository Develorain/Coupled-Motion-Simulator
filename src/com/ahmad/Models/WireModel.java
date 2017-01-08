package com.ahmad.Models;

import com.ahmad.Tools.Vector;

public abstract class WireModel {
    public Vector leftStringLeftCoord;
    public Vector leftStringRightCoord;

    public Vector rightStringLeftCoord;
    public Vector rightStringRightCoord;

    public double tension;

    public abstract void calculateCoordinates();
}

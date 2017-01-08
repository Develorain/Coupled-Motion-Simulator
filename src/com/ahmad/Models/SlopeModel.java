package com.ahmad.Models;

import com.ahmad.Tools.Vector;

public abstract class SlopeModel {
    public Vector leftCoord;
    public Vector rightCoord;

    public abstract void calculateCoordinates();
}

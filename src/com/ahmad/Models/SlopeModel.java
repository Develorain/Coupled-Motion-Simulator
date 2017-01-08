package com.ahmad.Models;

import com.ahmad.Tools.Vector;

public abstract class SlopeModel {
    public Vector leftCoord = Vector.createFromCartesian(0, 0);
    public Vector rightCoord = Vector.createFromCartesian(0, 0);

    public abstract void calculateCoordinates();
}

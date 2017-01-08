package com.ahmad.Models;

import com.ahmad.Tools.Vector;

public abstract class WireModel {
    public Vector leftStringLeftCoord = Vector.createFromCartesian(0, 0);
    public Vector leftStringRightCoord = Vector.createFromCartesian(0, 0);

    public Vector rightStringLeftCoord = Vector.createFromCartesian(0, 0);
    public Vector rightStringRightCoord = Vector.createFromCartesian(0, 0);

    public double tension;

    public abstract void updatePosition();
}

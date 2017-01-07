package com.ahmad.Models;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class PulleyModel {
    public Vector topLeftCorner;

    public int radius;
    public int diameter;

    public SystemModelModeOne systemModelModeOne;

    public PulleyModel(SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;

        radius = 50;
        diameter = radius * 2;

        calculateCoordinates();
    }

    public void calculateCoordinates() {
        topLeftCorner = Vector.createFromCartesian(
                systemModelModeOne.slope.x2 - radius + radius * MathTools.cos(systemModelModeOne.slopeAngle),
                systemModelModeOne.slope.y2 - radius - radius * MathTools.sin(systemModelModeOne.slopeAngle)
        );
    }
}

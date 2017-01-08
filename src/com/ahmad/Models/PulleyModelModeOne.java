package com.ahmad.Models;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class PulleyModelModeOne extends PulleyModel {
    private SystemModelModeOne systemModelModeOne;

    public PulleyModelModeOne(SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;

        radius = 50;
        diameter = radius * 2;

        calculateCoordinates();
    }

    public void calculateCoordinates() {
        topLeftCorner = Vector.createFromCartesian(
                systemModelModeOne.slope.rightCoord.getX() - radius + radius * MathTools.cos(systemModelModeOne.slopeAngle),
                systemModelModeOne.slope.rightCoord.getY() - radius - radius * MathTools.sin(systemModelModeOne.slopeAngle)
        );
    }
}

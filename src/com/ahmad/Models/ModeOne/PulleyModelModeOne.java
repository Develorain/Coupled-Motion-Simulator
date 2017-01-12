package com.ahmad.Models.ModeOne;

import com.ahmad.Models.PulleyModel;
import com.ahmad.Tools.MathTools;

public class PulleyModelModeOne extends PulleyModel {
    private SystemModelModeOne systemModelModeOne;

    public PulleyModelModeOne(SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;

        radius = 50;
        diameter = radius * 2;

        calculateCoordinates();
    }

    public void calculateCoordinates() {
        topLeftCorner.setX(systemModelModeOne.slope.rightCoord.getX() - radius + radius * MathTools.cos(systemModelModeOne.slopeAngle));
        topLeftCorner.setY(systemModelModeOne.slope.rightCoord.getY() - radius - radius * MathTools.sin(systemModelModeOne.slopeAngle));
    }
}

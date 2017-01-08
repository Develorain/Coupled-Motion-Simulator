package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.PulleyModel;
import com.ahmad.Tools.MathTools;

public class LeftPulleyModel extends PulleyModel {
    private SystemModelModeTwo systemModelModeTwo;

    public LeftPulleyModel(SystemModelModeTwo systemModelModeTwo) {
        this.systemModelModeTwo = systemModelModeTwo;

        radius = 25;
        diameter = radius * 2;

        calculateCoordinates();
    }

    public void calculateCoordinates() {
        topLeftCorner.setX(systemModelModeTwo.leftSlope.rightCoord.getX() - radius * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()) - radius);
        topLeftCorner.setY(systemModelModeTwo.leftSlope.rightCoord.getY() - diameter);
    }
}

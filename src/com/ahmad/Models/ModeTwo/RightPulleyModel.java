package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.PulleyModel;
import com.ahmad.Tools.MathTools;

public class RightPulleyModel extends PulleyModel {
    private SystemModelModeTwo systemModelModeTwo;

    public RightPulleyModel(SystemModelModeTwo systemModelModeTwo) {
        this.systemModelModeTwo = systemModelModeTwo;

        setRadius(25);

        calculateCoordinates();
    }

    public void calculateCoordinates() {
        topLeftCorner.setX(systemModelModeTwo.rightSlope.leftCoord.getX() - getRadius() + getRadius() * MathTools.sin(systemModelModeTwo.getRightSlopeAngle()));
        topLeftCorner.setY(systemModelModeTwo.rightSlope.leftCoord.getY() - getDiameter());
    }
}

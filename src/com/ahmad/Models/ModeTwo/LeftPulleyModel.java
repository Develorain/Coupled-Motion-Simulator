package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.PulleyModel;
import com.ahmad.Tools.MathTools;

public class LeftPulleyModel extends PulleyModel {
    private SystemModelModeTwo systemModelModeTwo;

    public LeftPulleyModel(SystemModelModeTwo systemModelModeTwo) {
        this.systemModelModeTwo = systemModelModeTwo;

        setRadius(25);

        calculateCoordinates();
    }

    public void calculateCoordinates() {
        topLeftCorner.setX(systemModelModeTwo.leftSlope.rightCoord.getX() - getRadius() * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()) - getRadius());
        topLeftCorner.setY(systemModelModeTwo.leftSlope.rightCoord.getY() - getDiameter());
    }
}

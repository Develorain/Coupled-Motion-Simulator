package com.ahmad.Models.ModeOne;

import com.ahmad.Models.PulleyModel;
import com.ahmad.Tools.MathTools;

public class PulleyModelModeOne extends PulleyModel {
    private SystemModelModeOne systemModelModeOne;

    public PulleyModelModeOne(SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;

        setRadius(50);

        calculateCoordinates();
    }

    public void calculateCoordinates() {
        topLeftCorner.setX(systemModelModeOne.slope.rightCoord.getX() - getRadius() + getRadius() * MathTools.cos(systemModelModeOne.slopeAngle));
        topLeftCorner.setY(systemModelModeOne.slope.rightCoord.getY() - getRadius() - getRadius() * MathTools.sin(systemModelModeOne.slopeAngle));
    }
}

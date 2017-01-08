package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.PulleyModel;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class LeftPulleyModel extends PulleyModel {
    private SystemModelModeTwo systemModelModeTwo;

    public LeftPulleyModel(SystemModelModeTwo systemModelModeTwo) {
        this.systemModelModeTwo = systemModelModeTwo;

        radius = 25;
        diameter = radius * 2;

        calculateCoordinates();
    }

    public void calculateCoordinates() {
        topLeftCorner = Vector.createFromCartesian(
                systemModelModeTwo.leftSlope.rightCoord.getX() - radius * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()) - radius,
                systemModelModeTwo.leftSlope.rightCoord.getY() - diameter
        );
    }
}

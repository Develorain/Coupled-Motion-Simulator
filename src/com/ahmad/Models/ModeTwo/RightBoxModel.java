package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.MathTools;

public class RightBoxModel extends BoxModel {
    private SystemModelModeTwo systemModelModeTwo;
    private double mu;

    public RightBoxModel(SystemModelModeTwo systemModelModeTwo, double mass, double mu) {
        super(mass);

        this.systemModelModeTwo = systemModelModeTwo;
        this.mu = mu;

        calculateStartingPositionCoordinates();
    }

    @Override
    public void calculateStartingPositionCoordinates() {
        double acuteAngle = 180 - systemModelModeTwo.getRightSlopeAngle();

        topLeftCorner.setX(systemModelModeTwo.rightSlope.rightCoord.getX() - boxWidth * MathTools.cos(acuteAngle) + boxWidth * MathTools.sin(acuteAngle));
        topLeftCorner.setY(systemModelModeTwo.rightSlope.rightCoord.getY() - boxHeight * MathTools.sin(acuteAngle) - boxHeight * MathTools.cos(acuteAngle));

        calculateBoxVerticesFromTopLeft();
    }

    @Override
    public void calculateBoxVerticesFromTopLeft() {
        double acuteAngle = 180 - systemModelModeTwo.getRightSlopeAngle();

        topRightCorner.setX((int) (topLeftCorner.getX() + boxWidth * MathTools.cos(acuteAngle)));
        topRightCorner.setY((int) (topLeftCorner.getY() + boxHeight * MathTools.sin(acuteAngle)));

        bottomLeftCorner.setX((int) (topLeftCorner.getX() - boxWidth * MathTools.sin(acuteAngle)));
        bottomLeftCorner.setY((int) (topLeftCorner.getY() + boxHeight * MathTools.cos(acuteAngle)));

        bottomRightCorner.setX((int) (bottomLeftCorner.getX() + boxWidth * MathTools.cos(acuteAngle)));
        bottomRightCorner.setY((int) (bottomLeftCorner.getY() + boxHeight * MathTools.sin(acuteAngle)));
    }

    public double getMu() {
        return mu;
    }
}
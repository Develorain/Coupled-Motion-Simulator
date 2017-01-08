package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.MathTools;

public class LeftBoxModel extends BoxModel {
    private SystemModelModeTwo systemModelModeTwo;
    private double mu;

    public LeftBoxModel(SystemModelModeTwo systemModelModeTwo, double mass, double mu) {
        super(mass);

        this.systemModelModeTwo = systemModelModeTwo;
        this.mu = mu;

        calculateStartingPositionCoordinates();
    }

    @Override
    public void calculateStartingPositionCoordinates() {
        topLeftCorner.setX(systemModelModeTwo.leftSlope.leftCoord.getX() - boxWidth * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()));
        topLeftCorner.setY(systemModelModeTwo.leftSlope.leftCoord.getY() - boxHeight * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle()));

        calculateBoxVerticesFromTopLeft();
    }

    @Override
    public void calculateBoxVerticesFromTopLeft() {
        topRightCorner.setX((int) (topLeftCorner.getX() + boxWidth * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle())));
        topRightCorner.setY((int) (topLeftCorner.getY() - boxHeight * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle())));

        bottomLeftCorner.setX((int) (topLeftCorner.getX() + boxWidth * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle())));
        bottomLeftCorner.setY((int) (topLeftCorner.getY() + boxHeight * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle())));

        bottomRightCorner.setX((int) (bottomLeftCorner.getX() + boxWidth * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle())));
        bottomRightCorner.setY((int) (bottomLeftCorner.getY() - boxHeight * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle())));
    }

    public double getMu() {
        return mu;
    }
}
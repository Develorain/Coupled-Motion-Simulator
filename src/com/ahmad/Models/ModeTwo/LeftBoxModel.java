package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class LeftBoxModel extends BoxModel {
    private SystemModelModeTwo systemModelModeTwo;
    public double mu;
    public double friction;

    public LeftBoxModel(SystemModelModeTwo systemModelModeTwo, double mass, double mu) {
        super(mass);

        this.systemModelModeTwo = systemModelModeTwo;
        this.mu = mu;

        calculateStartingPositionCoordinates();
    }

    public void updateFriction(double slopeAngle) {
        friction = mu * mass * Constants.GRAVITY * MathTools.cos(slopeAngle);
    }

    @Override
    public void calculateStartingPositionCoordinates() {
        topLeftCorner.setX((systemModelModeTwo.leftSlope.leftCoord.getX() + systemModelModeTwo.leftSlope.rightCoord.getX()) / 2
                - boxWidth * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()) - (boxWidth / 2) * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle()));
        topLeftCorner.setY((systemModelModeTwo.leftSlope.leftCoord.getY() + systemModelModeTwo.leftSlope.rightCoord.getY()) / 2
                - boxHeight * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle()) + (boxHeight / 2) * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()));

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
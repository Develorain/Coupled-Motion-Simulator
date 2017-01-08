package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

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
        topLeftCorner = Vector.createFromCartesian(
                systemModelModeTwo.leftSlope.leftCoord.getX() - boxWidth * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()),
                systemModelModeTwo.leftSlope.leftCoord.getY() - boxHeight * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle())
        );

        calculateBoxVerticesFromTopLeft();
    }

    @Override
    public void calculateBoxVerticesFromTopLeft() {
        topRightCorner = Vector.createFromCartesian(
                (int) (topLeftCorner.getX() + boxWidth * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle())),
                (int) (topLeftCorner.getY() - boxHeight * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()))
        );

        bottomLeftCorner = Vector.createFromCartesian(
                (int) (topLeftCorner.getX() + boxWidth * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle())),
                (int) (topLeftCorner.getY() + boxHeight * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle()))
        );

        bottomRightCorner = Vector.createFromCartesian(
                (int) (bottomLeftCorner.getX() + boxWidth * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle())),
                (int) (bottomLeftCorner.getY() - boxHeight * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle()))
        );
    }

    public double getMu() {
        return mu;
    }
}
package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class MiddleBoxModel extends BoxModel {
    private SystemModelModeTwo systemModelModeTwo;
    private double mu;

    public MiddleBoxModel(SystemModelModeTwo systemModelModeTwo, double mass, double mu) {
        super(mass);

        this.systemModelModeTwo = systemModelModeTwo;
        this.mu = mu;

        calculateStartingPositionCoordinates();
    }

    @Override
    public void calculateStartingPositionCoordinates() {
        topLeftCorner.setX(Constants.SIMULATION_WIDTH_PIXELS / 2 - boxWidth / 2);
        topLeftCorner.setY(Constants.SIMULATION_HEIGHT_PIXELS / 2 - boxHeight);

        calculateBoxVerticesFromTopLeft();
    }

    @Override
    public void calculateBoxVerticesFromTopLeft() {
        topRightCorner.setX(topLeftCorner.getX() + boxWidth * MathTools.cos(systemModelModeTwo.getMiddleSlopeAngle()));
        topRightCorner.setY(topLeftCorner.getY() - boxHeight * MathTools.sin(systemModelModeTwo.getMiddleSlopeAngle()));

        bottomLeftCorner.setX(topLeftCorner.getX() + boxWidth * MathTools.sin(systemModelModeTwo.getMiddleSlopeAngle()));
        bottomLeftCorner.setY(topLeftCorner.getY() + boxHeight * MathTools.cos(systemModelModeTwo.getMiddleSlopeAngle()));

        bottomRightCorner.setX(topRightCorner.getX() + boxWidth * MathTools.sin(systemModelModeTwo.getMiddleSlopeAngle()));
        bottomRightCorner.setY(topRightCorner.getY() + boxHeight * MathTools.cos(systemModelModeTwo.getMiddleSlopeAngle()));
    }

    public double getMu() {
        return mu;
    }
}
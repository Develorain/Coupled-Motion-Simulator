package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

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
        topLeftCorner = Vector.createFromCartesian(
                Constants.SIMULATION_WIDTH_PIXELS / 2 - boxWidth / 2,
                Constants.SIMULATION_HEIGHT_PIXELS / 2 - boxHeight
        );

        topRightCorner = Vector.createFromCartesian(
                topLeftCorner.getX() + boxWidth * MathTools.cos(systemModelModeTwo.getMiddleSlopeAngle()),
                topLeftCorner.getY() - boxHeight * MathTools.sin(systemModelModeTwo.getMiddleSlopeAngle())
        );

        bottomLeftCorner = Vector.createFromCartesian(
                topLeftCorner.getX() + boxWidth * MathTools.sin(systemModelModeTwo.getMiddleSlopeAngle()),
                topLeftCorner.getY() + boxHeight * MathTools.cos(systemModelModeTwo.getMiddleSlopeAngle())
        );

        bottomRightCorner = Vector.createFromCartesian(
                topRightCorner.getX() + boxWidth * MathTools.sin(systemModelModeTwo.getMiddleSlopeAngle()),
                topRightCorner.getY() + boxHeight * MathTools.cos(systemModelModeTwo.getMiddleSlopeAngle())
        );
    }

    @Override
    public void calculateBoxVerticesFromTopLeft() {

    }

    public double getMu() {
        return mu;
    }
}
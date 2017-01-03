package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class LeftBoxModel extends BoxModel {
    private SystemModelModeTwo systemModelModeTwo;
    private double mu;

    public LeftBoxModel(SystemModelModeTwo systemModelModeTwo, double mass, double mu) {
        super(mass);

        this.systemModelModeTwo = systemModelModeTwo;
        this.mu = mu;

        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        double tempX = Constants.SIMULATION_WIDTH_PIXELS / 3;
        double tempY = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        while (true) {
            tempX -= MathTools.cos(systemModelModeTwo.getLeftSlopeAngle());
            tempY += MathTools.sin(systemModelModeTwo.getLeftSlopeAngle());

            if (tempX <= boxWidth || tempY >= Constants.SIMULATION_HEIGHT_PIXELS - boxHeight) {
                // Set the x and y value to the calculated in-bounds coordinates
                x = tempX;
                y = tempY;

                // Offset the values so that the bottom of the box is displayed at this coordinate, not the top left corner
                x -= boxWidth * MathTools.sin(systemModelModeTwo.getLeftSlopeAngle());
                y -= boxHeight * MathTools.cos(systemModelModeTwo.getLeftSlopeAngle());

                break;
            }
        }
    }

    public double getMu() {
        return mu;
    }
}

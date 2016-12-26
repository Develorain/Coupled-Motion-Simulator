package com.ahmad.Models.ModeOne;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class SlopedBoxModel extends BoxModel {
    private SystemModelModeOne systemModelModeOne;
    private double mu;

    public SlopedBoxModel(SystemModelModeOne systemModelModeOne, double mass, double mu) {
        super(mass);

        this.systemModelModeOne = systemModelModeOne;
        this.mu = mu;

        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        double tempX = Constants.SIMULATION_WIDTH_PIXELS / 2;
        double tempY = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        while (true) {
            tempX -= MathTools.cos(systemModelModeOne.getSlopeAngle());
            tempY += MathTools.sin(systemModelModeOne.getSlopeAngle());

            if (tempX <= boxWidth || tempY >= Constants.SIMULATION_HEIGHT_PIXELS - boxHeight) {
                // Set the x and y value to the calculated in-bounds coordinates
                x = tempX;
                y = tempY;

                // Offset the values so that the bottom of the box is displayed at this coordinate, not the top left corner
                x -= boxWidth * MathTools.sin(systemModelModeOne.getSlopeAngle());
                y -= boxHeight * MathTools.cos(systemModelModeOne.getSlopeAngle());

                break;
            }
        }
    }

    public double getMu() {
        return mu;
    }
}

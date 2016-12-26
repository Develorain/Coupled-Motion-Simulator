package com.ahmad.Models.ModeOne;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class SlopedBoxModel extends BoxModel {
    private double mu;

    public SlopedBoxModel(SystemModelOne systemModelOne, double mass, double mu) {
        super(systemModelOne, mass);

        this.mu = mu;
    }

    @Override
    public void calculateCoordinates() {
        double tempX = Constants.SIMULATION_WIDTH_PIXELS / 2;
        double tempY = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        while (true) {
            tempX -= MathTools.cos(systemModelOne.getSlopeAngle());
            tempY += MathTools.sin(systemModelOne.getSlopeAngle());

            if (tempX <= boxWidth || tempY >= Constants.SIMULATION_HEIGHT_PIXELS - boxHeight) {
                // Set the x and y value to the calculated in-bounds coordinates
                x = tempX;
                y = tempY;

                // Offset the values so that the bottom of the box is displayed at this coordinate, not the top left corner
                x -= boxWidth * MathTools.sin(systemModelOne.getSlopeAngle());
                y -= boxHeight * MathTools.cos(systemModelOne.getSlopeAngle());

                break;
            }
        }
    }

    public double getMu() {
        return mu;
    }
}

package com.ahmad.Models.ModeOne;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class SlopedBoxModel extends BoxModel {
    private double mu;

    public SlopedBoxModel(SystemModel systemModel, double mass, double mu) {
        super(systemModel, mass);

        this.mu = mu;
    }

    public void calculateCoordinates() {
        double tempX = Constants.SIMULATION_WIDTH_PIXELS / 2;
        double tempY = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        while (true) {
            tempX -= MathTools.cos(systemModel.getSlopeAngle());
            tempY += MathTools.sin(systemModel.getSlopeAngle());

            if (tempX <= boxWidth || tempY >= Constants.SIMULATION_HEIGHT_PIXELS - boxHeight) {
                // Set the x and y value to the calculated in-bounds coordinates
                x = tempX;
                y = tempY;

                // Offset the values so that the bottom of the box is displayed at this coordinate, not the top left corner
                x -= boxWidth * MathTools.sin(systemModel.getSlopeAngle());
                y -= boxHeight * MathTools.cos(systemModel.getSlopeAngle());

                break;
            }
        }
    }

    public double getMu() {
        return mu;
    }
}

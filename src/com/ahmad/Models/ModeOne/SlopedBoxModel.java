package com.ahmad.Models.ModeOne;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.MathTools;

public class SlopedBoxModel extends BoxModel {
    private double mu;

    public SlopedBoxModel(SystemModel systemModel, double mass, double mu) {
        super(systemModel, mass);

        this.mu = mu;
    }

    public void calculateCoordinates() {
        double tempX = 600;
        double tempY = 350;

        while (true) {
            tempX -= MathTools.cos(systemModel.getSlopeAngle());
            tempY += MathTools.sin(systemModel.getSlopeAngle());

            if (tempX <= 100 || tempX >= 1000 || tempY <= 100 || tempY >= 600) {
                // Set the x and y value to the calculated in-bounds coordinates
                x = tempX;
                y = tempY;

                // Offset the values so that the bottom of the box is displayed at this coordinate, not the top left corner
                x -= 100 * MathTools.sin(systemModel.getSlopeAngle());
                y -= 100 * MathTools.cos(systemModel.getSlopeAngle());

                break;
            }
        }
    }

    public double getMu() {
        return mu;
    }
}

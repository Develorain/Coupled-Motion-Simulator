package com.ahmad.Models.ModeOne;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class SlopedBoxModel extends BoxModel {
    public SystemModelModeOne systemModelModeOne;
    public double friction;
    public double mu;

    public SlopedBoxModel(SystemModelModeOne systemModelModeOne, double mass, double mu) {
        super(mass);

        this.systemModelModeOne = systemModelModeOne;
        this.mu = mu;

        calculateCoordinates();
    }

    public void updateFriction(double angle) {
        friction = mu * mass * Constants.GRAVITY * MathTools.cos(angle);
    }

    public void updateFriction(double massRight, double acceleration, double angle) {
        // TODO: what if acceleration is 0? the problem is we don't know which dirction the friction will be, we will have to look at netforce instead of acceleration
        if (acceleration < 0) {
            friction = (mass + massRight) * acceleration - massRight * Constants.GRAVITY + mass * Constants.GRAVITY * MathTools.sin(angle);
        } else {
            friction = -(mass + massRight) * acceleration + massRight * Constants.GRAVITY - mass * Constants.GRAVITY * MathTools.sin(angle);
        }
    }

    public void updateMass(double angle) {
        mass = Math.abs(friction / (mu * Constants.GRAVITY * MathTools.cos(angle)));
    }

    public void updateMu(double angle) {
        mu = Math.abs(friction / (mass * Constants.GRAVITY * MathTools.cos(angle)));
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

package com.ahmad.Models.ModeOne;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class SlopedBoxModel extends BoxModel {
    public SystemModelModeOne systemModelModeOne;
    public double friction;
    public double mu;

    public SlopedBoxModel(SystemModelModeOne systemModelModeOne, double mass, double mu) {
        super(mass);

        this.systemModelModeOne = systemModelModeOne;
        this.mu = mu;

        // Calculate the coordinates of the box
        calculateStartingPositionCoordinates();
    }

    public void updateFriction(double angle) {
        friction = mu * mass * Constants.GRAVITY * MathTools.cos(angle);
    }

    public void updateFriction(double massRight, double acceleration, double angle) {
        // TODO: what if acceleration is 0? the problem is we don't know which direction the friction will be, we will have to look at netforce instead of acceleration
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
    public void calculateStartingPositionCoordinates() {
        // Calculate the coordinates of the four corners of the box based on its position
        topLeftCorner = Vector.createFromCartesian(
                systemModelModeOne.slope.x1 - boxWidth * MathTools.sin(systemModelModeOne.getSlopeAngle()),
                systemModelModeOne.slope.y1 - boxHeight * MathTools.cos(systemModelModeOne.getSlopeAngle())
        );

        topRightCorner = Vector.createFromCartesian(
                (int) (topLeftCorner.getX() + boxWidth * MathTools.cos(systemModelModeOne.getSlopeAngle())),
                (int) (topLeftCorner.getY() - boxHeight * MathTools.sin(systemModelModeOne.getSlopeAngle()))
        );

        bottomLeftCorner = Vector.createFromCartesian(
                (int) (topLeftCorner.getX() + boxWidth * MathTools.sin(systemModelModeOne.getSlopeAngle())),
                (int) (topLeftCorner.getY() + boxHeight * MathTools.cos(systemModelModeOne.getSlopeAngle()))
        );

        bottomRightCorner = Vector.createFromCartesian(
                (int) (topLeftCorner.getX() + boxWidth * MathTools.sin(systemModelModeOne.getSlopeAngle()) + boxWidth * MathTools.cos(systemModelModeOne.getSlopeAngle())),
                (int) (topLeftCorner.getY() + boxHeight * MathTools.cos(systemModelModeOne.getSlopeAngle()) - boxHeight * MathTools.sin(systemModelModeOne.getSlopeAngle()))
        );
    }

    public double getMu() {
        return mu;
    }
}
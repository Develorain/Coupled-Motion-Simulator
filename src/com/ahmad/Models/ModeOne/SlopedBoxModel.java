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

        calculateStartingPositionCoordinates();
    }

    @Override
    public void calculateStartingPositionCoordinates() {
        // Calculate the coordinates of the four corners of the box based on its position
        topLeftCorner.setX(systemModelModeOne.slope.leftCoord.getX() - boxWidth * MathTools.sin(systemModelModeOne.getSlopeAngle()));
        topLeftCorner.setY(systemModelModeOne.slope.leftCoord.getY() - boxHeight * MathTools.cos(systemModelModeOne.getSlopeAngle()));

        calculateBoxVerticesFromTopLeft();
    }

    // calculates top right, bottom left, and bottom right coords based on the top left corner
    @Override
    public void calculateBoxVerticesFromTopLeft() {
        topRightCorner.setX((int) (topLeftCorner.getX() + boxWidth * MathTools.cos(systemModelModeOne.getSlopeAngle())));
        topRightCorner.setY((int) (topLeftCorner.getY() - boxHeight * MathTools.sin(systemModelModeOne.getSlopeAngle())));

        bottomLeftCorner.setX((int) (topLeftCorner.getX() + boxWidth * MathTools.sin(systemModelModeOne.getSlopeAngle())));
        bottomLeftCorner.setY((int) (topLeftCorner.getY() + boxHeight * MathTools.cos(systemModelModeOne.getSlopeAngle())));

        bottomRightCorner.setX((int) (topLeftCorner.getX() + boxWidth * MathTools.sin(systemModelModeOne.getSlopeAngle()) + boxWidth * MathTools.cos(systemModelModeOne.getSlopeAngle())));
        bottomRightCorner.setY((int) (topLeftCorner.getY() + boxHeight * MathTools.cos(systemModelModeOne.getSlopeAngle()) - boxHeight * MathTools.sin(systemModelModeOne.getSlopeAngle())));
    }

    public void updateFriction(double angle) {
        friction = mu * mass * Constants.GRAVITY * MathTools.cos(angle);
    }

    public void updateMass(double angle, double massRight, double acceleration, double mu) {
        // if moving right
        if (acceleration > 0) {
            mass = (massRight * (Constants.GRAVITY - acceleration))
                    / (acceleration + Constants.GRAVITY * MathTools.sin(angle) + mu * Constants.GRAVITY * MathTools.cos(angle));
        } else if (acceleration < 0) {
            // if moving left
            mass = (massRight * (Constants.GRAVITY + acceleration))
                    / (Constants.GRAVITY * MathTools.sin(angle) - mu * Constants.GRAVITY * MathTools.cos(angle) - acceleration);
        }
    }

    public double getMu() {
        return mu;
    }
}
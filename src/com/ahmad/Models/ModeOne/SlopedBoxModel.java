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

    //TODO: i refactored this class so it does not create so many objects every iteration at the expense of some code repetition
    //TODO: to test it out, go to BoxModel class, and change updatePosition method to be abstract
    @Override
    public void updatePosition(double elapsedSeconds) {
        topLeftCorner.setX(topLeftCorner.getX() + (0.5 * acceleration.getX() * elapsedSeconds * elapsedSeconds));
        topLeftCorner.setY(topLeftCorner.getY() - (0.5 * acceleration.getY() * elapsedSeconds * elapsedSeconds));

        topRightCorner.setX((int) (topLeftCorner.getX() + boxWidth * MathTools.cos(systemModelModeOne.getSlopeAngle())));
        topRightCorner.setY((int) (topLeftCorner.getY() - boxHeight * MathTools.sin(systemModelModeOne.getSlopeAngle())));

        bottomLeftCorner.setX((int) (topLeftCorner.getX() + boxWidth * MathTools.sin(systemModelModeOne.getSlopeAngle())));
        bottomLeftCorner.setY((int) (topLeftCorner.getY() + boxHeight * MathTools.cos(systemModelModeOne.getSlopeAngle())));

        bottomRightCorner.setX((int) (topLeftCorner.getX() + boxWidth * MathTools.sin(systemModelModeOne.getSlopeAngle()) + boxWidth * MathTools.cos(systemModelModeOne.getSlopeAngle())));
        bottomRightCorner.setY((int) (topLeftCorner.getY() + boxHeight * MathTools.cos(systemModelModeOne.getSlopeAngle()) - boxHeight * MathTools.sin(systemModelModeOne.getSlopeAngle())));
    }

    @Override
    public void calculateStartingPositionCoordinates() {
        // Calculate the coordinates of the four corners of the box based on its position
        topLeftCorner = Vector.createFromCartesian(
                systemModelModeOne.slope.leftCoord.getX() - boxWidth * MathTools.sin(systemModelModeOne.getSlopeAngle()),
                systemModelModeOne.slope.leftCoord.getY() - boxHeight * MathTools.cos(systemModelModeOne.getSlopeAngle())
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

    @Override
    public void calculateBoxVerticesFromTopLeft() {
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

    public double getMu() {
        return mu;
    }
}
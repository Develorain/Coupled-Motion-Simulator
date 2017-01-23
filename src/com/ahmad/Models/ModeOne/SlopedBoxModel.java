package com.ahmad.Models.ModeOne;

/** SlopedBoxModel
 * Performs the calculations for sloped box in system in mode one
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class SlopedBoxModel extends BoxModel {
    private SystemModelModeOne systemModelModeOne;  // Declare the system model

    /** Default Constructor
     * @param systemModelModeOne  a reference to the system model
     * @param mass                the mass of the sloped box
     * @param mu                  the mu value of the sloped box */
    public SlopedBoxModel(SystemModelModeOne systemModelModeOne, double mass, double mu) {
        super(mass, mu);

        this.systemModelModeOne = systemModelModeOne;

        calculateStartingPositionCoordinates();
    }

    /** Calculates the starting position of the box */
    @Override
    public void calculateStartingPositionCoordinates() {
        // Calculate the coordinates of the four corners of the box based on its position
        topLeftCorner.setX((systemModelModeOne.getSlope().getLeftCoord().getX() + systemModelModeOne.getSlope().getRightCoord().getX()) / 2
                - BOX_WIDTH * MathTools.sin(systemModelModeOne.getSlopeAngle()) - (BOX_WIDTH / 2) * MathTools.cos(systemModelModeOne.getSlopeAngle()));
        topLeftCorner.setY((systemModelModeOne.getSlope().getLeftCoord().getY() + systemModelModeOne.getSlope().getRightCoord().getY()) / 2
                - BOX_HEIGHT * MathTools.cos(systemModelModeOne.getSlopeAngle()) + (BOX_HEIGHT / 2) * MathTools.sin(systemModelModeOne.getSlopeAngle()));

        calculateBoxVerticesFromTopLeft();
    }

    /** Calculates top right, bottom left, and bottom right coordinates based on the top left corner */
    @Override
    public void calculateBoxVerticesFromTopLeft() {
        topRightCorner.setX((int) (topLeftCorner.getX() + BOX_WIDTH * MathTools.cos(systemModelModeOne.getSlopeAngle())));
        topRightCorner.setY((int) (topLeftCorner.getY() - BOX_HEIGHT * MathTools.sin(systemModelModeOne.getSlopeAngle())));

        bottomLeftCorner.setX((int) (topLeftCorner.getX() + BOX_WIDTH * MathTools.sin(systemModelModeOne.getSlopeAngle())));
        bottomLeftCorner.setY((int) (topLeftCorner.getY() + BOX_HEIGHT * MathTools.cos(systemModelModeOne.getSlopeAngle())));

        bottomRightCorner.setX((int) (topLeftCorner.getX() + BOX_WIDTH * MathTools.sin(systemModelModeOne.getSlopeAngle()) + BOX_WIDTH * MathTools.cos(systemModelModeOne.getSlopeAngle())));
        bottomRightCorner.setY((int) (topLeftCorner.getY() + BOX_HEIGHT * MathTools.cos(systemModelModeOne.getSlopeAngle()) - BOX_HEIGHT * MathTools.sin(systemModelModeOne.getSlopeAngle())));
    }

    /** Updates the frictional force on the box
     * @param angle the angle the box is slope at in degrees */
    public void updateFriction(double angle) {
        friction = mu * mass * Constants.GRAVITY * MathTools.cos(angle);
    }

    /** Updates the mass of the sloped box
     * @param angle         the angle the box is sloped at in degrees
     * @param massRight     the mass of the box in kilograms
     * @param acceleration  the acceleration of the box in meters per second squared
     * @param mu            the mu value of the box */
    public void updateMass(double angle, double massRight, double acceleration, double mu) {
        // If the box is moving towards the right
        if (acceleration > 0) {
            mass = (massRight * (Constants.GRAVITY - acceleration))
                    / (acceleration + Constants.GRAVITY * MathTools.sin(angle) + mu * Constants.GRAVITY * MathTools.cos(angle));
        } else if (acceleration < 0) {
            // if moving left
            mass = (massRight * (Constants.GRAVITY + acceleration))
                    / (Constants.GRAVITY * MathTools.sin(angle) - mu * Constants.GRAVITY * MathTools.cos(angle) - acceleration);
        }
    }
}
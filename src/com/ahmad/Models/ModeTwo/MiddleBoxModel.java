package com.ahmad.Models.ModeTwo;

/** MiddleBoxModel
 * Performs the calculations for the middle box in system in mode two
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class MiddleBoxModel extends BoxModel {
    private SystemModelModeTwo systemModelModeTwo;  // Declare the system model

    /** Default Constructor
     * @param systemModelModeTwo a reference to the system model
     * @param mass               the mass of the left box
     * @param mu                 the mu value of the left box */
    public MiddleBoxModel(SystemModelModeTwo systemModelModeTwo, double mass, double mu) {
        super(mass, mu);

        this.systemModelModeTwo = systemModelModeTwo;

        calculateStartingPositionCoordinates();
    }

    /** Calculates the starting position of the box */
    @Override
    public void calculateStartingPositionCoordinates() {
        topLeftCorner.setX(Constants.SIMULATION_WIDTH_PIXELS / 2 - BOX_WIDTH / 2);
        topLeftCorner.setY(Constants.SIMULATION_HEIGHT_PIXELS / 2 - BOX_HEIGHT);

        calculateBoxVerticesFromTopLeft();
    }

    /** Calculates top right, bottom left, and bottom right coordinates based on the top left corner */
    @Override
    public void calculateBoxVerticesFromTopLeft() {
        topRightCorner.setX(topLeftCorner.getX() + BOX_WIDTH * MathTools.cos(systemModelModeTwo.getMiddleSlopeAngle()));
        topRightCorner.setY(topLeftCorner.getY() - BOX_HEIGHT * MathTools.sin(systemModelModeTwo.getMiddleSlopeAngle()));

        bottomLeftCorner.setX(topLeftCorner.getX() + BOX_WIDTH * MathTools.sin(systemModelModeTwo.getMiddleSlopeAngle()));
        bottomLeftCorner.setY(topLeftCorner.getY() + BOX_HEIGHT * MathTools.cos(systemModelModeTwo.getMiddleSlopeAngle()));

        bottomRightCorner.setX(topRightCorner.getX() + BOX_WIDTH * MathTools.sin(systemModelModeTwo.getMiddleSlopeAngle()));
        bottomRightCorner.setY(topRightCorner.getY() + BOX_HEIGHT * MathTools.cos(systemModelModeTwo.getMiddleSlopeAngle()));
    }

    /** Updates the frictional force on the box
     * @param slopeAngle the angle the box is slope at in degrees */
    public void updateFriction(double slopeAngle) {
        friction = Math.abs(mu * mass * Constants.GRAVITY * MathTools.cos(slopeAngle));
    }
}
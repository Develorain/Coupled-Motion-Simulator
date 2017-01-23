package com.ahmad.Models.ModeOne;

/** DanglingBoxModel
 * Performs the calculations for dangling box in system in mode one
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Constants;

public class DanglingBoxModel extends BoxModel {
    private SystemModelModeOne systemModelModeOne;  // Declare the system model

    /** Default Constructor
     * @param systemModelModeOne  the reference to the system model
     * @param mass                the mass of the dangling box */
    public DanglingBoxModel(SystemModelModeOne systemModelModeOne, double mass) {
        super(mass, 0);

        this.systemModelModeOne = systemModelModeOne;

        calculateStartingPositionCoordinates();
    }

    /** Calculates the starting position coordinates of the dangling box */
    @Override
    public void calculateStartingPositionCoordinates() {
        topLeftCorner.setX(systemModelModeOne.getPulley().getTopLeftCorner().getX() + systemModelModeOne.getPulley().getRadius());
        topLeftCorner.setY((systemModelModeOne.getSlope().getRightCoord().getY() + Constants.SIMULATION_HEIGHT_PIXELS) / 2 - (BOX_HEIGHT / 2));

        calculateBoxVerticesFromTopLeft();
    }

    /** Calculates top right, bottom left, and bottom right coordinates based on the top left corner */
    @Override
    public void calculateBoxVerticesFromTopLeft() {
        topRightCorner.setX((int) (topLeftCorner.getX() + BOX_WIDTH));
        topRightCorner.setY((int) (topLeftCorner.getY()));

        bottomLeftCorner.setX((int) (topLeftCorner.getX()));
        bottomLeftCorner.setY((int) (topLeftCorner.getY() + BOX_HEIGHT));

        bottomRightCorner.setX((int) (topLeftCorner.getX() + BOX_WIDTH));
        bottomRightCorner.setY((int) (topLeftCorner.getY() + BOX_HEIGHT));
    }

    /** Updates the gravitational force on the dangling box */
    public void updateGravForce() {
        super.updateGravForce(90);
    }
}
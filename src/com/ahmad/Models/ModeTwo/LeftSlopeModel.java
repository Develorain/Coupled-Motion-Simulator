package com.ahmad.Models.ModeTwo;

/** LeftSlopeModel
 * Performs the calculations for the left slope in system in mode two
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class LeftSlopeModel extends SlopeModel {
    private SystemModelModeTwo systemModelModeTwo;   // Declare the system model

    /** Default Constructor
     * @param systemModelModeTwo  a reference to the system model */
    public LeftSlopeModel(SystemModelModeTwo systemModelModeTwo) {
        super();
        this.systemModelModeTwo = systemModelModeTwo;
        calculateCoordinates();
    }

    /** Calculates the coordinates of the slope model */
    @Override
    public void calculateCoordinates() {
        double tempX = Constants.SIMULATION_WIDTH_PIXELS / 3;
        double tempY = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        while (true) {
            tempX -= MathTools.cos(systemModelModeTwo.getLeftSlopeAngle());
            tempY += MathTools.sin(systemModelModeTwo.getLeftSlopeAngle());

            if (tempX <= 0 || tempY >= Constants.SIMULATION_HEIGHT_PIXELS) {
                // Set the x and y value to the calculated in-bounds coordinates
                leftCoord.setX(tempX);
                leftCoord.setY(tempY);

                break;
            }
        }

        rightCoord.setX(Constants.SIMULATION_WIDTH_PIXELS / 3);
        rightCoord.setY(Constants.SIMULATION_HEIGHT_PIXELS / 2);
    }
}

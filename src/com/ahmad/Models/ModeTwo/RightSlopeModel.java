package com.ahmad.Models.ModeTwo;

/** RightSlopeModel
 * Performs the calculations for the right slope in system in mode two
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class RightSlopeModel extends SlopeModel {
    private SystemModelModeTwo systemModelModeTwo;   // Declare the system model

    /** Default Constructor
     * @param systemModelModeTwo  a reference to the system model */
    public RightSlopeModel(SystemModelModeTwo systemModelModeTwo) {
        super();
        this.systemModelModeTwo = systemModelModeTwo;
        calculateCoordinates();
    }

    /** Calculates the coordinates of the slope model */
    @Override
    public void calculateCoordinates() {
        leftCoord.setX(Constants.SIMULATION_WIDTH_PIXELS * 2 / 3);
        leftCoord.setY(Constants.SIMULATION_HEIGHT_PIXELS / 2);

        double tempX = leftCoord.getX();
        double tempY = leftCoord.getY();

        while (true) {
            tempX -= MathTools.cos(systemModelModeTwo.getRightSlopeAngle());
            tempY += MathTools.sin(systemModelModeTwo.getRightSlopeAngle());

            if (tempX >= Constants.SIMULATION_WIDTH_PIXELS || tempY >= Constants.SIMULATION_HEIGHT_PIXELS) {
                rightCoord.setX(tempX);
                rightCoord.setY(tempY);

                break;
            }
        }
    }
}
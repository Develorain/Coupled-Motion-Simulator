package com.ahmad.Models.ModeTwo;

/** MiddleSlopeModel
 * Performs the calculations for the middle slope in system in mode two
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.Constants;

public class MiddleSlopeModel extends SlopeModel {
    /** Default Constructor */
    public MiddleSlopeModel() {
        super();
        calculateCoordinates();
    }

    /** Calculates the coordinates of the slope model */
    @Override
    public void calculateCoordinates() {
        leftCoord.setX(Constants.SIMULATION_WIDTH_PIXELS / 3);
        leftCoord.setY(Constants.SIMULATION_HEIGHT_PIXELS / 2);

        rightCoord.setX(Constants.SIMULATION_WIDTH_PIXELS * 2 / 3);
        rightCoord.setY(Constants.SIMULATION_HEIGHT_PIXELS / 2);
    }
}

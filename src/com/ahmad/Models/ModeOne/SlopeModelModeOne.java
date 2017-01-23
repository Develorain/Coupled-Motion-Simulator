package com.ahmad.Models.ModeOne;

/** SlopeModelModeOne
 * Performs the calculations for slope in system in mode one
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class SlopeModelModeOne extends SlopeModel {
    private SystemModelModeOne systemModelModeOne;   // Declare the system model

    /** Default Constructor
     * @param systemModelModeOne  a reference to the system model */
    public SlopeModelModeOne(SystemModelModeOne systemModelModeOne) {
        super();
        this.systemModelModeOne = systemModelModeOne;
        calculateCoordinates();
    }

    /** Calculates the coordinates of the slope model */
    @Override
    public void calculateCoordinates() {
        // Code for left coord
        double tempX = Constants.SIMULATION_WIDTH_PIXELS / 2;
        double tempY = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        // Find bottom left corner of the slope
        while (true) {
            tempX -= MathTools.cos(systemModelModeOne.getSlopeAngle());
            tempY += MathTools.sin(systemModelModeOne.getSlopeAngle());

            if (tempX <= 0 || tempY >= Constants.SIMULATION_HEIGHT_PIXELS) {
                // Set the x and y value to the calculated in-bounds coordinates
                leftCoord.setX(tempX);
                leftCoord.setY(tempY);

                break;
            }
        }

        // Code for right coord
        double tempX2 = Constants.SIMULATION_WIDTH_PIXELS / 2;
        double tempY2 = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        // Find the top left corner of the slope
        while (true) {
            tempX2 += MathTools.cos(systemModelModeOne.getSlopeAngle());
            tempY2 -= MathTools.sin(systemModelModeOne.getSlopeAngle());

            if (tempX2 >= Constants.SIMULATION_WIDTH_PIXELS - (2 * 100) || tempY2 <= 100) {
                // Set the x and y value to the calculated in-bounds coordinates
                rightCoord.setX(tempX2);
                rightCoord.setY(tempY2);

                break;
            }
        }
    }
}
package com.ahmad.Models.ModeOne;

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class SlopeModelModeOne extends SlopeModel {
    private SystemModelModeOne systemModelModeOne;

    public SlopeModelModeOne(SystemModelModeOne systemModelModeOne) {
        super();
        this.systemModelModeOne = systemModelModeOne;
        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        // Code for left coord
        double tempX = Constants.SIMULATION_WIDTH_PIXELS / 2;
        double tempY = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        // Find bottom left corner of the slope
        while (true) {
            tempX -= MathTools.cos(systemModelModeOne.getSlopeAngle());
            tempY += MathTools.sin(systemModelModeOne.getSlopeAngle());

            // TODO: remove hardcoded 100 value. this value represents how many pixels the slope will stop from the edge of the window
            if (tempX <= 100 || tempY >= Constants.SIMULATION_HEIGHT_PIXELS - 100) {
                // Set the x and y value to the calculated in-bounds coordinates
                x1 = (int) tempX;
                y1 = (int) tempY;

                break;
            }
        }

        //////////////////////////

        // Code for right coord
        double tempX2 = Constants.SIMULATION_WIDTH_PIXELS / 2;
        double tempY2 = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        // Find the top left corner of the slope
        while (true) {
            tempX2 += MathTools.cos(systemModelModeOne.getSlopeAngle());
            tempY2 -= MathTools.sin(systemModelModeOne.getSlopeAngle());

            // TODO: remove hardcoded 100 value. this value represents how many pixels the slope will stop from the edge of the window
            if (tempX2 >= Constants.SIMULATION_WIDTH_PIXELS - (2 * 100) || tempY2 <= 100) {
                // Set the x and y value to the calculated in-bounds coordinates
                x2 = (int) tempX2;
                y2 = (int) tempY2;

                break;
            }
        }
    }
}
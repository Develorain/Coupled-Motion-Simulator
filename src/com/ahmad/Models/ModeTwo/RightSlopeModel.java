package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;

public class RightSlopeModel extends SlopeModel {
    private SystemModelModeTwo systemModelModeTwo;

    public RightSlopeModel(SystemModelModeTwo systemModelModeTwo) {
        super();
        this.systemModelModeTwo = systemModelModeTwo;
        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        x1 = Constants.SIMULATION_WIDTH_PIXELS * 2 / 3;
        y1 = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        double tempX = x1;
        double tempY = y1;

        while (true) {
            tempX -= MathTools.cos(systemModelModeTwo.getRightSlopeAngle());
            tempY += MathTools.sin(systemModelModeTwo.getRightSlopeAngle());

            // Todo: hardcoded 100... remove later
            if (tempX >= Constants.SIMULATION_WIDTH_PIXELS - 100 || tempY >= Constants.SIMULATION_HEIGHT_PIXELS - 100) {
                // Set the x and y value to the calculated in-bounds coordinates
                x2 = (int) tempX;
                y2 = (int) tempY;

                break;
            }
        }
    }
}
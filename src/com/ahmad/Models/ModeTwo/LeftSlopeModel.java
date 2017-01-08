package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.MathTools;
import com.ahmad.Tools.Vector;

public class LeftSlopeModel extends SlopeModel {
    private SystemModelModeTwo systemModelModeTwo;

    public LeftSlopeModel(SystemModelModeTwo systemModelModeTwo) {
        super();
        this.systemModelModeTwo = systemModelModeTwo;
        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        double tempX = Constants.SIMULATION_WIDTH_PIXELS / 3;
        double tempY = Constants.SIMULATION_HEIGHT_PIXELS / 2;

        while (true) {
            tempX -= MathTools.cos(systemModelModeTwo.getLeftSlopeAngle());
            tempY += MathTools.sin(systemModelModeTwo.getLeftSlopeAngle());

            // TODO: 100 hardcoded value... remove later
            if (tempX <= 100 || tempY >= Constants.SIMULATION_HEIGHT_PIXELS - 100) {
                // Set the x and y value to the calculated in-bounds coordinates
                leftCoord = Vector.createFromCartesian(tempX, tempY);

                break;
            }
        }

        rightCoord = Vector.createFromCartesian(Constants.SIMULATION_WIDTH_PIXELS / 3, Constants.SIMULATION_HEIGHT_PIXELS / 2);
    }
}

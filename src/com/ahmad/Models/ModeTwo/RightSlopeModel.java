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
        leftCoord.setX(Constants.SIMULATION_WIDTH_PIXELS * 2 / 3);
        leftCoord.setY(Constants.SIMULATION_HEIGHT_PIXELS / 2);

        double tempX = leftCoord.getX();
        double tempY = leftCoord.getY();

        while (true) {
            tempX -= MathTools.cos(systemModelModeTwo.getRightSlopeAngle());
            tempY += MathTools.sin(systemModelModeTwo.getRightSlopeAngle());

            // Todo: hardcoded 100... remove later
            if (tempX >= Constants.SIMULATION_WIDTH_PIXELS - 100 || tempY >= Constants.SIMULATION_HEIGHT_PIXELS - 100) {
                rightCoord.setX(tempX);
                rightCoord.setY(tempY);

                break;
            }
        }
    }
}
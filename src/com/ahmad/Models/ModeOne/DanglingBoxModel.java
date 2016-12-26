package com.ahmad.Models.ModeOne;

import com.ahmad.Models.BoxModel;
import com.ahmad.Tools.Globals;
import com.ahmad.Tools.MathTools;

public class DanglingBoxModel extends BoxModel {
    private SystemModelModeOne systemModelModeOne;

    public DanglingBoxModel(SystemModelModeOne systemModelModeOne, double mass) {
        super(mass);

        this.systemModelModeOne = systemModelModeOne;

        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        double tempX = Globals.SIMULATION_WIDTH_PIXELS / 2;
        double tempY = Globals.SIMULATION_HEIGHT_PIXELS / 2;

        while (true) {
            tempX += MathTools.cos(systemModelModeOne.getSlopeAngle());
            tempY -= MathTools.sin(systemModelModeOne.getSlopeAngle());

            if (tempX >= Globals.SIMULATION_WIDTH_PIXELS - (2 * boxWidth) || tempY <= boxHeight) {
                // Set the x and y value to the calculated in-bounds coordinates
                x = tempX;
                y = tempY;

                break;
            }
        }
    }
}
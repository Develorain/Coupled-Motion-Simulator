package com.ahmad.Models.ModeTwo;

import com.ahmad.Models.SlopeModel;
import com.ahmad.Tools.Globals;

public class MiddleSlopeModel extends SlopeModel {
    private SystemModelModeTwo systemModelModeTwo;

    public MiddleSlopeModel(SystemModelModeTwo systemModelModeTwo) {
        super();
        this.systemModelModeTwo = systemModelModeTwo;
        calculateCoordinates();
    }

    @Override
    public void calculateCoordinates() {
        x1 = Globals.SIMULATION_WIDTH_PIXELS / 3;
        y1 = Globals.SIMULATION_HEIGHT_PIXELS / 2;

        x2 = Globals.SIMULATION_WIDTH_PIXELS * 2 / 3;
        y2 = Globals.SIMULATION_HEIGHT_PIXELS / 2;
    }
}

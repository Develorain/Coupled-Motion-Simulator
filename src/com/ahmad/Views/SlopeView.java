package com.ahmad.Views;

import com.ahmad.Models.ModeOne.SystemModel;

import java.awt.*;

public class SlopeView {
    private SystemModel boxSystem;

    public SlopeView(SystemModel boxSystem) {
        this.boxSystem = boxSystem;
    }

    // Draw slope
    public void draw(Graphics graphics) {
        graphics.drawLine(boxSystem.slopeA.x1, boxSystem.slopeA.y1, boxSystem.slopeA.x2, boxSystem.slopeA.y2);
    }
}

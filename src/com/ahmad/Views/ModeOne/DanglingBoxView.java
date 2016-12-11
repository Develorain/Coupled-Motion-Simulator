package com.ahmad.Views.ModeOne;

import com.ahmad.Models.ModeOne.SystemModel;

import java.awt.*;

public class DanglingBoxView {
    private SystemModel boxSystem;

    public DanglingBoxView(SystemModel boxSystem) {
        this.boxSystem = boxSystem;
    }

    // Draws the danglingBox based on its coordinates
    public void draw(Graphics graphics) {
        graphics.fillRect(boxSystem.getBoxB().getX(), boxSystem.getBoxB().getY(), boxSystem.getBoxB().getWidth(), boxSystem.getBoxB().getHeight());
    }
}

package com.ahmad.Views;

import com.ahmad.Views.ModeOne.DanglingBoxView;
import com.ahmad.Views.ModeOne.SlopedBoxView;
import com.ahmad.Models.ModeOne.SystemModel;

import javax.swing.*;
import java.awt.*;

public class SystemView extends JPanel {
    private SystemModel boxSystem;

    private SlopedBoxView slopedBoxView;
    private DanglingBoxView danglingBoxView;

    private SlopeView slopeView;

    private double slopeAngle;

    public SystemView(SystemModel boxSystem) {
        super();

        setPreferredSize(new Dimension(1200, 700));

        this.boxSystem = boxSystem;

        slopedBoxView = new SlopedBoxView(boxSystem);
        danglingBoxView = new DanglingBoxView(boxSystem);

        slopeView = new SlopeView(boxSystem);

        slopeAngle = boxSystem.getSlopeAngle();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        slopedBoxView.draw(graphics, slopeAngle);
        danglingBoxView.draw(graphics);

        slopeView.draw(graphics);
    }

    public void setSlopeAngle(double slopeAngle) {
        this.slopeAngle = slopeAngle;
    }

    public SystemModel getBoxSystem() {
        return boxSystem;
    }
}

//graphics.drawLine(0, 0, getWidth() / 2, getHeight() / 2);
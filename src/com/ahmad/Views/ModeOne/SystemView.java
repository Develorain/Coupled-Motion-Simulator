package com.ahmad.Views.ModeOne;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.CustomPanel;
import com.ahmad.Tools.Paintable;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class SystemView implements Paintable {
    public CustomPanel systemPanel;

    private SystemModelModeOne systemModelModeOne;

    public SystemView(SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;

        systemPanel = new CustomPanel(this);
        systemPanel.setPreferredSize(new Dimension(Constants.SIMULATION_WIDTH_PIXELS, Constants.SIMULATION_HEIGHT_PIXELS));
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawLine(0, 0, Constants.SIMULATION_WIDTH_PIXELS / 2, Constants.SIMULATION_HEIGHT_PIXELS / 2);

        drawSlopedBox((Graphics2D) graphics);
        drawDanglingBox(graphics);
        drawSlope(graphics);
    }

    private void drawSlopedBox(Graphics2D graphics) {
        // Creates a rectangle rotated based on the box's angle
        double theta = Math.toRadians(systemModelModeOne.getSlopeAngle());
        Rectangle2D rectangle2D = new Rectangle2D.Double(0, 0, systemModelModeOne.getSlopedBox().getBoxWidth(), systemModelModeOne.getSlopedBox().getBoxHeight());
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(-theta);

        // Create a shape based on the rotated rectangle
        Shape rotatedRectangle = affineTransform.createTransformedShape(rectangle2D);

        // Sets up Graphics2D object
        Graphics2D graphics2D = graphics;
        graphics2D.setColor(new Color(51, 51, 51));    // mild gray colour

        // Moves origin to x, y coordinate of the rectangle, draws it, and then returns origin back to 0, 0
        graphics2D.translate(systemModelModeOne.getSlopedBox().getX(), systemModelModeOne.getSlopedBox().getY());
        graphics2D.fill(rotatedRectangle);
        graphics2D.translate(-systemModelModeOne.getSlopedBox().getX(), -systemModelModeOne.getSlopedBox().getY());
    }

    private void drawDanglingBox(Graphics graphics) {
        graphics.fillRect(systemModelModeOne.getDanglingBox().getX(), systemModelModeOne.getDanglingBox().getY(), systemModelModeOne.getDanglingBox().getBoxWidth(), systemModelModeOne.getDanglingBox().getBoxHeight());
    }

    private void drawSlope(Graphics graphics) {
        graphics.drawLine(systemModelModeOne.slope.x1, systemModelModeOne.slope.y1, systemModelModeOne.slope.x2, systemModelModeOne.slope.y2);
    }
}

//graphics.drawLine(0, 0, getBoxWidth() / 2, getBoxHeight() / 2);
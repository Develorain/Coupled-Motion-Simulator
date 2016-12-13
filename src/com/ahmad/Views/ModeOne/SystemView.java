package com.ahmad.Views.ModeOne;

import com.ahmad.Tools.CustomPanel;
import com.ahmad.Tools.Paintable;
import com.ahmad.Models.ModeOne.SystemModel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class SystemView implements Paintable {
    public CustomPanel systemPanel;

    private SystemModel systemModel;

    public SystemView(SystemModel systemModel) {
        this.systemModel = systemModel;

        systemPanel = new CustomPanel(this);
        systemPanel.setPreferredSize(new Dimension(1200, 700));
    }

    @Override
    public void paint(Graphics graphics) {
        drawSlopedBox((Graphics2D) graphics);
        drawDanglingBox(graphics);
        drawSlope(graphics);
    }

    private void drawSlopedBox(Graphics2D graphics) {
        // Creates a rectangle rotated based on the box's angle
        double theta = Math.toRadians(systemModel.getSlopeAngle());
        Rectangle2D rectangle2D = new Rectangle2D.Double(0, 0, 100, 100);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(-theta);

        // Create a shape based on the rotated rectangle
        Shape rotatedRectangle = affineTransform.createTransformedShape(rectangle2D);

        // Sets up Graphics2D object
        Graphics2D graphics2D = graphics;
        graphics2D.setColor(new Color(51, 51, 51));    // mild gray colour

        // Moves origin to x, y coordinate of the rectangle, draws it, and then returns origin back to 0, 0
        graphics2D.translate(systemModel.getBoxA().getX(), systemModel.getBoxA().getY());
        graphics2D.fill(rotatedRectangle);
        graphics2D.translate(-systemModel.getBoxA().getX(), -systemModel.getBoxA().getY());
    }

    private void drawDanglingBox(Graphics graphics) {
        graphics.fillRect(systemModel.getBoxB().getX(), systemModel.getBoxB().getY(), systemModel.getBoxB().getWidth(), systemModel.getBoxB().getHeight());
    }

    private void drawSlope(Graphics graphics) {
        graphics.drawLine(systemModel.slopeA.x1, systemModel.slopeA.y1, systemModel.slopeA.x2, systemModel.slopeA.y2);
    }
}

//graphics.drawLine(0, 0, getWidth() / 2, getHeight() / 2);
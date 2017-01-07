package com.ahmad.Tools;

import com.ahmad.Models.BoxModel;
import com.ahmad.Models.PulleyModel;
import com.ahmad.Models.SlopeModel;
import com.ahmad.Models.WireModel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class GraphicsPainter {
    public static void drawSlopedBox(Graphics graphics, BoxModel box, double slopeAngle, boolean invertedAxis) {
        if (invertedAxis) {
            slopeAngle -= 180;
        }

        // Creates a rectangle rotated based on the box's angle
        double theta = Math.toRadians(slopeAngle);
        Rectangle2D rectangle2D = new Rectangle2D.Double(0, 0, box.getBoxWidth(), box.getBoxHeight());
        AffineTransform affineTransform = new AffineTransform();

        affineTransform.rotate(-theta);

        // Create a shape based on the rotated rectangle
        Shape rotatedRectangle = affineTransform.createTransformedShape(rectangle2D);

        // Sets up Graphics2D object
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(new Color(51, 51, 51));    // mild gray colour

        // Moves origin to x, y coordinate of the rectangle, draws it, and then returns origin back to 0, 0
        graphics2D.translate(box.topLeftCorner.getX(), box.topLeftCorner.getY());
        graphics2D.fill(rotatedRectangle);
        graphics2D.translate(-box.topLeftCorner.getX(), -box.topLeftCorner.getY());
    }

    public static void drawDanglingBox(Graphics graphics, BoxModel box) {
        graphics.fillRect((int) box.topLeftCorner.getX(), (int) box.topLeftCorner.getY(), box.getBoxWidth(), box.getBoxHeight());
    }

    public static void drawSlope(Graphics graphics, SlopeModel slope) {
        graphics.drawLine(slope.x1, slope.y1, slope.x2, slope.y2);
    }

    public static void drawWire(Graphics graphics, WireModel wire) {
        graphics.drawLine(wire.x1, wire.y1, wire.x2, wire.y2);

        graphics.drawLine(wire.x3, wire.y3, wire.x4, wire.y4);
    }

    public static void drawPulley(Graphics graphics, PulleyModel pulley) {
        graphics.fillOval((int) pulley.topLeftCorner.getX(), (int) pulley.topLeftCorner.getY(), pulley.diameter, pulley.diameter);
    }
}
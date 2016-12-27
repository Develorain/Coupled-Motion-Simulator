package com.ahmad.Tools;

import com.ahmad.Models.BoxModel;
import com.ahmad.Models.SlopeModel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class GraphicsPainter {
    public static void drawSlopedBox(Graphics graphics, BoxModel box, double slopeAngle, boolean isLeft) {
        // Creates a rectangle rotated based on the box's angle
        double theta = Math.toRadians(slopeAngle);
        Rectangle2D rectangle2D = new Rectangle2D.Double(0, 0, box.getBoxWidth(), box.getBoxHeight());
        AffineTransform affineTransform = new AffineTransform();

        affineTransform.rotate(isLeft ? -theta : theta);

        // Create a shape based on the rotated rectangle
        Shape rotatedRectangle = affineTransform.createTransformedShape(rectangle2D);

        // Sets up Graphics2D object
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(new Color(51, 51, 51));    // mild gray colour

        // Moves origin to x, y coordinate of the rectangle, draws it, and then returns origin back to 0, 0
        graphics2D.translate(box.getX(), box.getY());
        graphics2D.draw(rotatedRectangle);
        graphics2D.translate(-box.getX(), -box.getY());
    }

    public static void drawDanglingBox(Graphics graphics, BoxModel box) {
        graphics.fillRect(box.getX(), box.getY(), box.getBoxWidth(), box.getBoxHeight());
    }

    public static void drawSlope(Graphics graphics, SlopeModel slope) {
        graphics.drawLine(slope.x1, slope.y1, slope.x2, slope.y2);
    }
}

//graphics.drawLine(0, 0, getBoxWidth() / 2, getBoxHeight() / 2);
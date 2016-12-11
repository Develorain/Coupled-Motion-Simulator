package com.ahmad.Views.ModeOne;

import com.ahmad.Models.ModeOne.SystemModel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class SlopedBoxView {
    private SystemModel boxSystem;

    public SlopedBoxView(SystemModel boxSystem) {
        this.boxSystem = boxSystem;
    }

    // Draw sloped box
    public void draw(Graphics graphics, double slopeAngle) {
        // Creates a rectangle rotated based on the box's angle
        double theta = Math.toRadians(slopeAngle);
        Rectangle2D rectangle2D = new Rectangle2D.Double(0, 0, 100, 100);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(-theta);

        // Create a shape based on the rotated rectangle
        Shape rotatedRectangle = affineTransform.createTransformedShape(rectangle2D);

        // Sets up Graphics2D object
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(new Color(51, 51, 51));    // mild gray colour

        // Moves origin to x, y coordinate of the rectangle, draws it, and then returns origin back to 0, 0
        graphics2D.translate(boxSystem.getBoxA().getX(), boxSystem.getBoxA().getY());
        graphics2D.fill(rotatedRectangle);
        graphics2D.translate(-boxSystem.getBoxA().getX(), -boxSystem.getBoxA().getY());
    }
}

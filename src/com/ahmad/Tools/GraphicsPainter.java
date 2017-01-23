package com.ahmad.Tools;

/** GraphicsPainter
 * Utility class to draw graphics to screen
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.BoxModel;
import com.ahmad.Models.PulleyModel;
import com.ahmad.Models.SlopeModel;
import com.ahmad.Models.WireModel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class GraphicsPainter {
    /** Draws the sloped box to the screen
     * @param graphics     a reference to the graphics instance
     * @param box          a reference to the box instance
     * @param slopeAngle   the slope angle
     * @param invertedAxis whether the x-axis is inverted or not */
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
        graphics2D.translate(box.getTopLeftCorner().getX(), box.getTopLeftCorner().getY());
        graphics2D.fill(rotatedRectangle);
        graphics2D.translate(-box.getTopLeftCorner().getX(), -box.getTopLeftCorner().getY());
    }

    /** Draws the dangling box to the screen
     * @param graphics a reference to the graphics instance
     * @param box      a reference to the box instance */
    public static void drawDanglingBox(Graphics graphics, BoxModel box) {
        graphics.fillRect(
                (int) box.getTopLeftCorner().getX(),
                (int) box.getTopLeftCorner().getY(),
                box.getBoxWidth(),
                box.getBoxHeight()
        );
    }

    /** Draws the slope to the screen
     * @param graphics a reference to the graphics instance
     * @param slope    a reference to the slope instance */
    public static void drawSlope(Graphics graphics, SlopeModel slope) {
        graphics.drawLine(
                (int) slope.getLeftCoord().getX(),
                (int) slope.getLeftCoord().getY(),
                (int) slope.getRightCoord().getX(),
                (int) slope.getRightCoord().getY()
        );
    }

    /** Draws the wire to the screen
     * @param graphics a reference to the graphics instance
     * @param wire     a reference to the wire instance */
    public static void drawWire(Graphics graphics, WireModel wire) {
        graphics.drawLine(
                (int) wire.getLeftWireLeftCoord().getX(),
                (int) wire.getLeftWireLeftCoord().getY(),
                (int) wire.getLeftWireRightCoord().getX(),
                (int) wire.getLeftWireRightCoord().getY()
        );

        graphics.drawLine(
                (int) wire.getRightWireLeftCoord().getX(),
                (int) wire.getRightWireLeftCoord().getY(),
                (int) wire.getRightWireRightCoord().getX(),
                (int) wire.getRightWireRightCoord().getY()
        );
    }

    /** Draws the pulley to the screen
     * @param graphics a reference to the graphics instance
     * @param pulley   a reference to the pulley instance */
    public static void drawPulley(Graphics graphics, PulleyModel pulley) {
        graphics.fillOval(
                (int) pulley.getTopLeftCorner().getX(),
                (int) pulley.getTopLeftCorner().getY(),
                (int) pulley.getDiameter(),
                (int) pulley.getDiameter()
        );
    }

    /** Draws the vertical slope from below the pulley to the bottom of the scren
     * @param graphics a reference to the graphics instance
     * @param slope    a reference to the slope instance */
    public static void drawVerticalLine(Graphics graphics, SlopeModel slope) {
        graphics.drawLine(
                (int) slope.getRightCoord().getX(),
                (int) slope.getRightCoord().getY(),
                (int) slope.getRightCoord().getX(),
                Constants.SIMULATION_HEIGHT_PIXELS);
    }
}
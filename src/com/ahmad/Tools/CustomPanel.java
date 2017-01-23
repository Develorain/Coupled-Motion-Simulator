package com.ahmad.Tools;

/** CustomPanel
 * Inherits JPanel to provide custom paint functionality to a Paintable
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    private Paintable paintable;   // Declare the paintable

    /** Default Constructor
     * @param paintable reference to paintable instance */
    public CustomPanel(Paintable paintable) {
        this.paintable = paintable;
    }

    /** Paints the component
     * @param graphics reference to graphics instance */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        paintable.paint(graphics);
    }
}

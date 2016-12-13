package com.ahmad.Tools;

import javax.swing.*;
import java.awt.*;

public class CustomPanel extends JPanel {
    private Paintable paintable;

    public CustomPanel(Paintable paintable) {
        this.paintable = paintable;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        paintable.paint(graphics);
        repaint();
    }
}

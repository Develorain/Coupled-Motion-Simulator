package com.ahmad.Views;

import com.ahmad.Tools.CustomPanel;
import com.ahmad.Tools.Paintable;
import com.ahmad.Views.ModeOne.DanglingBoxView;
import com.ahmad.Views.ModeOne.SlopedBoxView;
import com.ahmad.Models.ModeOne.SystemModel;

import java.awt.*;

public class SystemView implements Paintable {
    public CustomPanel systemPanel;

    private SystemModel systemModel;

    private SlopedBoxView slopedBoxView;
    private DanglingBoxView danglingBoxView;

    private SlopeView slopeView;

    public SystemView(SystemModel systemModel) {
        this.systemModel = systemModel;

        slopedBoxView = new SlopedBoxView(systemModel);
        danglingBoxView = new DanglingBoxView(systemModel);

        slopeView = new SlopeView(systemModel);

        systemPanel = new CustomPanel(this);
        systemPanel.setPreferredSize(new Dimension(1200, 700));
    }

    @Override
    public void paint(Graphics graphics) {
        slopedBoxView.draw(graphics);
        danglingBoxView.draw(graphics);

        slopeView.draw(graphics);

        // Repaints the window after updating everything
        systemPanel.repaint();
    }
}

//graphics.drawLine(0, 0, getWidth() / 2, getHeight() / 2);
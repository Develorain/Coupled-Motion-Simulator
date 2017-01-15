package com.ahmad.Tools;

import java.awt.*;

public class Constants {
    public static final double GRAVITY = 9.8;
    public static final int UPDATES_PER_SECOND = 100;
    public static final int SIMULATION_WIDTH_PIXELS = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth() * 0.9); // 1250 make sure it's not under 700 pixels. the reason why it doesn't look centered below 700 pixels, is because the frame doesn't shrink in size, but the simulation view does
    public static final int SIMULATION_HEIGHT_PIXELS = (int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight() * 0.7); // 600
}
package com.ahmad.Tools;

public class Globals {
    public static final double GRAVITY = 9.8;
    public static final int UPDATES_PER_SECOND = 100;
    public static final double DELTA_TIME_SECONDS = 1.0 / UPDATES_PER_SECOND;
    public static final int SIMULATION_WIDTH_PIXELS = 1200; // 1200 make sure it's not under 700 pixels. the reason why it doesn't look centered below 700 pixels, is because the frame doesn't shrink in size, but the simulation view does
    public static final int SIMULATION_HEIGHT_PIXELS = 700; // 700

    public static long simulationStartTime = 0;
}
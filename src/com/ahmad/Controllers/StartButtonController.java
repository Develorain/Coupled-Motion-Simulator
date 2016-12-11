package com.ahmad.Controllers;

import com.ahmad.Models.ModeOne.SystemModel;
import com.ahmad.Views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class StartButtonController implements ActionListener {
    private Timer timer;

    private MainView view;
    private SystemModel boxSystem;

    public StartButtonController(MainView view, SystemModel boxSystem) {
        this.view = view;
        this.boxSystem = boxSystem;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Start Button Pressed");

        boxSystem.update();

        TimerTask timerTask = new TimerTask() {
            public void run() {
                // Updates the boxes' velocities
                boxSystem.getBoxA().updateVelocity();
                boxSystem.getBoxB().updateVelocity();

                // Updates the boxes' positions
                boxSystem.getBoxA().updatePosition();
                boxSystem.getBoxB().updatePosition();

                // Updates the box system's information table
                view.updateBoxSystemInfoTable();

                // Repaints the window after updating everything
                view.repaint();
            }
        };

        // to stop the timer after x amount of seconds: http://stackoverflow.com/questions/4252187/how-to-stop-execution-after-a-certain-time-in-java

        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 1000 / 60, 1000 / 60);
    }
}
package com.ahmad.Controllers;

import com.ahmad.Models.ModeOne.SystemModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class StartButtonController implements ActionListener {
    private Timer timer;

    private SystemModel systemModel;

    public StartButtonController(SystemModel systemModel) {
        this.systemModel = systemModel;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Start Button Pressed");

        systemModel.initializeConstantValues();

        TimerTask timerTask = new TimerTask() {
            public void run() {
                systemModel.iterate(); // TODO: needs to check if start button was already pressed to avoid multiple timers running
            }
        };

        // to stop the timer after x amount of seconds: http://stackoverflow.com/questions/4252187/how-to-stop-execution-after-a-certain-time-in-java

        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 1000 / 60, 1000 / 60);
    }

}
package com.ahmad.Controllers;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Tools.Constants;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class StartButtonController implements ActionListener {
    private Timer timer;

    private SystemModelModeOne systemModelModeOne;

    public StartButtonController(SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Start Button Pressed");

        systemModelModeOne.initializeConstantValues();

        TimerTask timerTask = new TimerTask() {
            public void run() {
                systemModelModeOne.iterate(); // TODO: needs to check if start button was already pressed to avoid multiple timers running
            }
        };

        // to stop the timer after x amount of seconds: http://stackoverflow.com/questions/4252187/how-to-stop-execution-after-a-certain-time-in-java

        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, (int) (Constants.DELTA_TIME_SECONDS * 1000), (int) (Constants.DELTA_TIME_SECONDS * 1000));
    }

}
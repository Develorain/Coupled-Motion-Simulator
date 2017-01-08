package com.ahmad.Controllers;

import com.ahmad.Models.SystemModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Views.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class StartButtonController implements ActionListener {
    private View mainView;
    private SystemModel systemModel;

    private Timer timer;

    // TODO: I'm thinking this class needs to be more specific for each mode in order for me to disable all the components
    // TODO: So make a super class where there are two StartButtonControllers extend for each mode
    public StartButtonController(View mainView, SystemModel systemModel) {
        this.mainView = mainView;
        this.systemModel = systemModel;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        systemModel.takeInputAndInitializeConstantValues(mainView);

        TimerTask timerTask = new TimerTask() {
            public void run() {
                systemModel.iterate(); // TODO: needs to check if start button was already pressed to avoid multiple timers running
            }
        };

        // to stop the timer after x amount of seconds: http://stackoverflow.com/questions/4252187/how-to-stop-execution-after-a-certain-time-in-java

        timer = new Timer();

        timer.scheduleAtFixedRate(timerTask, 0, 1000 / Constants.UPDATES_PER_SECOND);
    }

}
package com.ahmad.Controllers;

import com.ahmad.Models.SystemModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Views.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class StartButtonController implements ActionListener {
    private View view;
    private SystemModel systemModel;

    public StartButtonController(View view, SystemModel systemModel) {
        this.view = view;
        this.systemModel = systemModel;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        systemModel.setActive();

        systemModel.takeInputAndInitializeConstantValues(view);

        TimerTask timerTask = new TimerTask() {
            public void run() {
                systemModel.iterate();
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000 / Constants.UPDATES_PER_SECOND);
    }
}

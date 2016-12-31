package com.ahmad.Controllers;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Models.SystemModel;
import com.ahmad.Tools.Globals;
import com.ahmad.Views.ModeOne.MainViewModeOne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class StartButtonController implements ActionListener {
    private MainViewModeOne mainView;
    private SystemModel systemModel;

    private Timer timer;

    public StartButtonController(MainViewModeOne mainView, SystemModel systemModel) {
        this.mainView = mainView;
        this.systemModel = systemModel;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        SystemModelModeOne z = (SystemModelModeOne) systemModel;

        //z.accelerationOfSystem = Double.parseDouble(mainView.accelerationTextField.getText());

        z.getSlopedBox().mass = Double.parseDouble(mainView.leftBoxMassTextField.getText());

        z.getSlopedBox().mu = Double.parseDouble(mainView.leftBoxMuTextField.getText());

        z.getDanglingBox().mass = Double.parseDouble(mainView.rightBoxMassTextField.getText());

        z.slopeAngle = Double.parseDouble(mainView.leftSlopeAngleTextField.getText());

        systemModel.initializeConstantValues();

        TimerTask timerTask = new TimerTask() {
            public void run() {
                systemModel.iterate(); // TODO: needs to check if start button was already pressed to avoid multiple timers running
            }
        };

        // to stop the timer after x amount of seconds: http://stackoverflow.com/questions/4252187/how-to-stop-execution-after-a-certain-time-in-java

        timer = new Timer();

        timer.scheduleAtFixedRate(timerTask, 0, 1000 / Globals.UPDATES_PER_SECOND);
    }

}
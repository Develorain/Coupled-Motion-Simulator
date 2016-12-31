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
        SystemModelModeOne sysModel = (SystemModelModeOne) systemModel;

        int scenario = -1;

        if (!mainView.leftBoxMassTextField.getText().isEmpty() && !mainView.rightBoxMassTextField.getText().isEmpty()
                && !mainView.leftBoxMuTextField.getText().isEmpty() && !mainView.leftSlopeAngleTextField.getText().isEmpty()) {
            scenario = 1;

            sysModel.getSlopedBox().mass = Double.parseDouble(mainView.leftBoxMassTextField.getText());
            sysModel.getDanglingBox().mass = Double.parseDouble(mainView.rightBoxMassTextField.getText());
            sysModel.getSlopedBox().mu = Double.parseDouble(mainView.leftBoxMuTextField.getText());
            sysModel.slopeAngle = Double.parseDouble(mainView.leftSlopeAngleTextField.getText());

        } else if (!mainView.rightBoxMassTextField.getText().isEmpty() && !mainView.tensionTextField.getText().isEmpty()
                && !mainView.leftSlopeAngleTextField.getText().isEmpty() && !mainView.leftBoxMuTextField.getText().isEmpty()) {
            scenario = 2;

            sysModel.getDanglingBox().mass = Double.parseDouble(mainView.rightBoxMassTextField.getText());
            sysModel.wire.tension = Double.parseDouble(mainView.tensionTextField.getText());
            sysModel.slopeAngle = Double.parseDouble(mainView.leftSlopeAngleTextField.getText());
            sysModel.getSlopedBox().mu = Double.parseDouble(mainView.leftBoxMuTextField.getText());

        } else if (!mainView.accelerationTextField.getText().isEmpty() && !mainView.tensionTextField.getText().isEmpty()
                && !mainView.leftSlopeAngleTextField.getText().isEmpty() && !mainView.leftBoxMassTextField.getText().isEmpty()) {
            scenario = 3;

            sysModel.accelerationOfSystem = Double.parseDouble(mainView.accelerationTextField.getText());
            sysModel.wire.tension = Double.parseDouble(mainView.tensionTextField.getText());
            sysModel.slopeAngle = Double.parseDouble(mainView.leftSlopeAngleTextField.getText());
            sysModel.getSlopedBox().mass = Double.parseDouble(mainView.leftBoxMassTextField.getText());
        } else {
            System.out.println("Missing input");
            return;
        }

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
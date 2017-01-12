package com.ahmad.Controllers;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Views.ModeOne.MainViewModeOne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButtonControllerModeOne extends StartButtonController implements ActionListener {
    private MainViewModeOne mainViewModeOne;

    public StartButtonControllerModeOne(MainViewModeOne mainViewModeOne, SystemModelModeOne systemModelModeOne) {
        super(mainViewModeOne, systemModelModeOne);

        this.mainViewModeOne = mainViewModeOne;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        super.actionPerformed(actionEvent);

        mainViewModeOne.accelerationTextField.setEditable(false);
        mainViewModeOne.velocityTextField.setEditable(false);
        mainViewModeOne.tensionTextField.setEditable(false);
        mainViewModeOne.leftBoxMassTextField.setEditable(false);
        mainViewModeOne.leftBoxMuTextField.setEditable(false);
        mainViewModeOne.leftBoxFrictionTextField.setEditable(false);
        mainViewModeOne.leftSlopeAngleTextField.setEditable(false);
        mainViewModeOne.rightBoxMassTextField.setEditable(false);

        mainViewModeOne.inputTypeComboBox.setEnabled(false);
        mainViewModeOne.startButton.setEnabled(false);
    }
}


// TODO: I'm thinking this class needs to be more specific for each mode in order for me to disable all the components
// TODO: So make a super class where there are two StartButtonControllers extend for each mode

// TODO: needs to check if start button was already pressed to avoid multiple timers running
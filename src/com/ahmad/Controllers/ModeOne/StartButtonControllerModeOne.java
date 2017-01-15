package com.ahmad.Controllers.ModeOne;

import com.ahmad.Controllers.StartButtonController;
import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Views.ModeOne.MainViewModeOne;

import java.awt.event.ActionEvent;

public class StartButtonControllerModeOne extends StartButtonController {
    private MainViewModeOne mainViewModeOne;
    private SystemModelModeOne systemModelModeOne;

    public StartButtonControllerModeOne(MainViewModeOne mainViewModeOne, SystemModelModeOne systemModelModeOne) {
        super(mainViewModeOne, systemModelModeOne);

        this.mainViewModeOne = mainViewModeOne;
        this.systemModelModeOne = systemModelModeOne;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        super.actionPerformed(actionEvent);

        systemModelModeOne.isActive = true;

        mainViewModeOne.accelerationTextField.setEditable(false);
        mainViewModeOne.velocityTextField.setEditable(false);
        mainViewModeOne.tensionTextField.setEditable(false);
        mainViewModeOne.leftBoxMassTextField.setEditable(false);
        mainViewModeOne.leftBoxMuTextField.setEditable(false);
        mainViewModeOne.leftBoxFrictionTextField.setEditable(false);
        mainViewModeOne.leftSlopeAngleTextField.setEditable(false);
        mainViewModeOne.leftSlopeAngleTextField.setFocusable(false);
        mainViewModeOne.rightBoxMassTextField.setEditable(false);
        mainViewModeOne.inputTypeComboBox.setEnabled(false);
        mainViewModeOne.startButton.setEnabled(false);

        mainViewModeOne.accelerationTextField.setFocusable(false);
        mainViewModeOne.velocityTextField.setFocusable(false);
        mainViewModeOne.tensionTextField.setFocusable(false);
        mainViewModeOne.leftBoxMassTextField.setFocusable(false);
        mainViewModeOne.leftBoxMuTextField.setFocusable(false);
        mainViewModeOne.leftBoxFrictionTextField.setFocusable(false);
        mainViewModeOne.leftSlopeAngleTextField.setFocusable(false);
        mainViewModeOne.leftSlopeAngleTextField.setFocusable(false);
        mainViewModeOne.rightBoxMassTextField.setFocusable(false);
        mainViewModeOne.inputTypeComboBox.setFocusable(false);
        mainViewModeOne.startButton.setFocusable(false);
    }
}
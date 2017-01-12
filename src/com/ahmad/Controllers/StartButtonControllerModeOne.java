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
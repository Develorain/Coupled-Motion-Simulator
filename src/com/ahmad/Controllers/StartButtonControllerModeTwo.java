package com.ahmad.Controllers;

import com.ahmad.Models.ModeTwo.SystemModelModeTwo;
import com.ahmad.Views.ModeTwo.MainViewModeTwo;

import java.awt.event.ActionEvent;

public class StartButtonControllerModeTwo extends StartButtonController {
    private MainViewModeTwo mainViewModeTwo;

    public StartButtonControllerModeTwo(MainViewModeTwo mainViewModeTwo, SystemModelModeTwo systemModelModeTwo) {
        super(mainViewModeTwo, systemModelModeTwo);

        this.mainViewModeTwo = mainViewModeTwo;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        super.actionPerformed(actionEvent);

        mainViewModeTwo.accelerationTextField.setEditable(false);
        mainViewModeTwo.velocityTextField.setEditable(false);
        mainViewModeTwo.leftBoxMassTextField.setEditable(false);
        mainViewModeTwo.leftBoxMuTextField.setEditable(false);
        mainViewModeTwo.leftBoxFrictionTextField.setEditable(false);
        mainViewModeTwo.leftWireTensionTextField.setEditable(false);
        mainViewModeTwo.leftSlopeAngleTextField.setEditable(false);
        mainViewModeTwo.middleBoxMassTextField.setEditable(false);
        mainViewModeTwo.middleBoxMuTextField.setEditable(false);
        mainViewModeTwo.middleBoxFrictionTextField.setEditable(false);
        mainViewModeTwo.rightBoxMassTextField.setEditable(false);
        mainViewModeTwo.rightBoxMuTextField.setEditable(false);
        mainViewModeTwo.rightBoxFrictionTextField.setEditable(false);
        mainViewModeTwo.rightWireTensionTextField.setEditable(false);
        mainViewModeTwo.rightSlopeAngleTextField.setEditable(false);

        mainViewModeTwo.inputTypeComboBox.setEnabled(false);
        mainViewModeTwo.startButton.setEnabled(false);
    }
}
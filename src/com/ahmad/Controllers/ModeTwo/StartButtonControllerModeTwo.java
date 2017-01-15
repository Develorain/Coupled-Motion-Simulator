package com.ahmad.Controllers.ModeTwo;

import com.ahmad.Controllers.StartButtonController;
import com.ahmad.Models.ModeTwo.SystemModelModeTwo;
import com.ahmad.Views.ModeTwo.MainViewModeTwo;

import java.awt.event.ActionEvent;

public class StartButtonControllerModeTwo extends StartButtonController {
    private MainViewModeTwo mainViewModeTwo;
    private SystemModelModeTwo systemModelModeTwo;

    public StartButtonControllerModeTwo(MainViewModeTwo mainViewModeTwo, SystemModelModeTwo systemModelModeTwo) {
        super(mainViewModeTwo, systemModelModeTwo);

        this.mainViewModeTwo = mainViewModeTwo;
        this.systemModelModeTwo = systemModelModeTwo;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        super.actionPerformed(actionEvent);

        systemModelModeTwo.isActive = true;

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

        mainViewModeTwo.accelerationTextField.setFocusable(false);
        mainViewModeTwo.velocityTextField.setFocusable(false);
        mainViewModeTwo.leftBoxMassTextField.setFocusable(false);
        mainViewModeTwo.leftBoxMuTextField.setFocusable(false);
        mainViewModeTwo.leftBoxFrictionTextField.setFocusable(false);
        mainViewModeTwo.leftWireTensionTextField.setFocusable(false);
        mainViewModeTwo.leftSlopeAngleTextField.setFocusable(false);
        mainViewModeTwo.middleBoxMassTextField.setFocusable(false);
        mainViewModeTwo.middleBoxMuTextField.setFocusable(false);
        mainViewModeTwo.middleBoxFrictionTextField.setFocusable(false);
        mainViewModeTwo.rightBoxMassTextField.setFocusable(false);
        mainViewModeTwo.rightBoxMuTextField.setFocusable(false);
        mainViewModeTwo.rightBoxFrictionTextField.setFocusable(false);
        mainViewModeTwo.rightWireTensionTextField.setFocusable(false);
        mainViewModeTwo.rightSlopeAngleTextField.setFocusable(false);
        mainViewModeTwo.inputTypeComboBox.setFocusable(false);
        mainViewModeTwo.startButton.setFocusable(false);
    }
}
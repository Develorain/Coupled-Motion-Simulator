package com.ahmad.Controllers;

import com.ahmad.Views.ModeOne.MainViewModeOne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputTypeComboBoxController implements ActionListener {
    private MainViewModeOne mainViewModeOne;

    public InputTypeComboBoxController(MainViewModeOne mainViewModeOne) {
        this.mainViewModeOne = mainViewModeOne;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int choice = mainViewModeOne.inputTypeComboBox.getSelectedIndex();

        // < CLEAR THE VALUES IN ALL TEXT FIELDS
        mainViewModeOne.accelerationTextField.setText("");
        mainViewModeOne.tensionTextField.setText("");

        mainViewModeOne.leftBoxMassTextField.setText("");
        mainViewModeOne.leftBoxMuTextField.setText("");
        mainViewModeOne.leftBoxFrictionTextField.setText("");

        mainViewModeOne.leftSlopeAngleTextField.setText("");

        mainViewModeOne.rightBoxMassTextField.setText("");


        // < SET EVERYTHING TO EDITABLE >
        mainViewModeOne.accelerationTextField.setEditable(true);
        mainViewModeOne.tensionTextField.setEditable(true);

        mainViewModeOne.leftBoxMassTextField.setEditable(true);
        mainViewModeOne.leftBoxMuTextField.setEditable(true);
        mainViewModeOne.leftBoxFrictionTextField.setEditable(true);

        mainViewModeOne.leftSlopeAngleTextField.setEditable(true);

        mainViewModeOne.rightBoxMassTextField.setEditable(true);


        // < SET THE NON-REQUIRED FIELDS TO UNEDITABLE DEPENDING ON WHICH INPUT TYPE IS SELECTED >
        switch (choice) {
            case 0:
                mainViewModeOne.accelerationTextField.setEditable(false);
                mainViewModeOne.tensionTextField.setEditable(false);
                mainViewModeOne.leftBoxFrictionTextField.setEditable(false);

                break;

            case 1:
                mainViewModeOne.accelerationTextField.setEditable(false);
                mainViewModeOne.leftBoxMassTextField.setEditable(false);

                break;

            case 2:
                mainViewModeOne.leftBoxMuTextField.setEditable(false);
                mainViewModeOne.leftBoxFrictionTextField.setEditable(false);
                mainViewModeOne.rightBoxMassTextField.setEditable(false);

                break;
        }
    }
}

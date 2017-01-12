package com.ahmad.Controllers.ModeOne;

import com.ahmad.Views.ModeOne.MainViewModeOne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputTypeComboBoxControllerModeOne implements ActionListener {
    private MainViewModeOne mainViewModeOne;

    public InputTypeComboBoxControllerModeOne(MainViewModeOne mainViewModeOne) {
        this.mainViewModeOne = mainViewModeOne;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // < CLEAR THE VALUES IN ALL TEXT FIELDS >
        mainViewModeOne.accelerationTextField.setText("");
        mainViewModeOne.velocityTextField.setText("");
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
        switch (mainViewModeOne.inputTypeComboBox.getSelectedIndex()) {
            case 0:
                mainViewModeOne.accelerationTextField.setEditable(false);
                mainViewModeOne.tensionTextField.setEditable(false);
                mainViewModeOne.leftBoxFrictionTextField.setEditable(false);

                // Set default values
                mainViewModeOne.leftBoxMassTextField.setText("1");
                mainViewModeOne.leftBoxMuTextField.setText("0");

                mainViewModeOne.leftSlopeAngleTextField.setText("45");

                mainViewModeOne.rightBoxMassTextField.setText("1");

                break;

            case 1:
                mainViewModeOne.accelerationTextField.setEditable(false);
                mainViewModeOne.leftBoxFrictionTextField.setEditable(false);
                mainViewModeOne.leftBoxMassTextField.setEditable(false);

                mainViewModeOne.tensionTextField.setText("0");

                mainViewModeOne.leftBoxMuTextField.setText("0");

                mainViewModeOne.leftSlopeAngleTextField.setText("45");

                mainViewModeOne.rightBoxMassTextField.setText("1");

                break;
        }
    }
}

package com.ahmad.Controllers.ModeTwo;

import com.ahmad.Views.ModeTwo.MainViewModeTwo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputTypeComboBoxControllerModeTwo implements ActionListener {
    private MainViewModeTwo mainViewModeTwo;

    public InputTypeComboBoxControllerModeTwo(MainViewModeTwo mainViewModeTwo) {
        this.mainViewModeTwo = mainViewModeTwo;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // < CLEAR THE VALUES IN ALL TEXT FIELDS >
        mainViewModeTwo.accelerationTextField.setText("");

        mainViewModeTwo.leftBoxMassTextField.setText("");
        mainViewModeTwo.leftBoxMuTextField.setText("");
        mainViewModeTwo.leftBoxFrictionTextField.setText("");

        mainViewModeTwo.leftWireTensionTextField.setText("");

        mainViewModeTwo.leftSlopeAngleTextField.setText("");

        mainViewModeTwo.middleBoxMassTextField.setText("");
        mainViewModeTwo.middleBoxMuTextField.setText("");
        mainViewModeTwo.middleBoxFrictionTextField.setText("");

        mainViewModeTwo.rightBoxMassTextField.setText("");
        mainViewModeTwo.rightBoxMuTextField.setText("");
        mainViewModeTwo.rightBoxFrictionTextField.setText("");

        mainViewModeTwo.rightWireTensionTextField.setText("");

        mainViewModeTwo.rightSlopeAngleTextField.setText("");

        // < SET EVERYTHING TO EDITABLE >
        mainViewModeTwo.accelerationTextField.setEditable(true);

        mainViewModeTwo.leftBoxMassTextField.setEditable(true);
        mainViewModeTwo.leftBoxMuTextField.setEditable(true);
        mainViewModeTwo.leftBoxFrictionTextField.setEditable(true);

        mainViewModeTwo.leftWireTensionTextField.setEditable(true);

        mainViewModeTwo.leftSlopeAngleTextField.setEditable(true);

        mainViewModeTwo.middleBoxMassTextField.setEditable(true);
        mainViewModeTwo.middleBoxMuTextField.setEditable(true);
        mainViewModeTwo.middleBoxFrictionTextField.setEditable(true);

        mainViewModeTwo.rightBoxMassTextField.setEditable(true);
        mainViewModeTwo.rightBoxMuTextField.setEditable(true);
        mainViewModeTwo.rightBoxFrictionTextField.setEditable(true);

        mainViewModeTwo.rightWireTensionTextField.setEditable(true);

        mainViewModeTwo.rightSlopeAngleTextField.setEditable(true);


        // < SET THE NON-REQUIRED FIELDS TO UNEDITABLE DEPENDING ON WHICH INPUT TYPE IS SELECTED >
        switch (mainViewModeTwo.inputTypeComboBox.getSelectedIndex()) {
            case 0:
                mainViewModeTwo.accelerationTextField.setEditable(false);

                mainViewModeTwo.leftBoxFrictionTextField.setEditable(false);

                mainViewModeTwo.leftWireTensionTextField.setEditable(false);

                mainViewModeTwo.middleBoxFrictionTextField.setEditable(false);

                mainViewModeTwo.rightBoxFrictionTextField.setEditable(false);

                mainViewModeTwo.rightWireTensionTextField.setEditable(false);

                // Set default values
                mainViewModeTwo.leftBoxMassTextField.setText("1");
                mainViewModeTwo.leftBoxMuTextField.setText("0");

                mainViewModeTwo.leftSlopeAngleTextField.setText("45");

                mainViewModeTwo.middleBoxMassTextField.setText("1");
                mainViewModeTwo.middleBoxMuTextField.setText("1");

                mainViewModeTwo.rightBoxMassTextField.setText("1");
                mainViewModeTwo.rightBoxMuTextField.setText("0");

                mainViewModeTwo.rightSlopeAngleTextField.setText("45");

                break;

            case 1:

                break;
        }
    }
}

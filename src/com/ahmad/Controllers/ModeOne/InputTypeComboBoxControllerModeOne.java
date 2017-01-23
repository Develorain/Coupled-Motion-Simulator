package com.ahmad.Controllers.ModeOne;

/** InputTypeComboBoxControllerModeOne
 * Handles input type combo box in mode one
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Views.ModeOne.MainViewModeOne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputTypeComboBoxControllerModeOne implements ActionListener {
    private MainViewModeOne mainViewModeOne;   // Declare the main view

    /** Default Constructor
     * @param mainViewModeOne a reference to the view */
    public InputTypeComboBoxControllerModeOne(MainViewModeOne mainViewModeOne) {
        this.mainViewModeOne = mainViewModeOne;
    }

    /** Listens for an action to be performed on the combo box
     * @param actionEvent the event created upon interaction */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Clear the values in all text fields
        mainViewModeOne.getAccelerationTextField().setText("");
        mainViewModeOne.getVelocityTextField().setText("");
        mainViewModeOne.getTensionTextField().setText("");

        mainViewModeOne.getLeftBoxMassTextField().setText("");
        mainViewModeOne.getLeftBoxMuTextField().setText("");
        mainViewModeOne.getLeftBoxFrictionTextField().setText("");

        mainViewModeOne.getLeftSlopeAngleTextField().setText("");

        mainViewModeOne.getRightBoxMassTextField().setText("");


        // Set everything to editable
        mainViewModeOne.getAccelerationTextField().setEditable(true);
        mainViewModeOne.getTensionTextField().setEditable(true);

        mainViewModeOne.getLeftBoxMassTextField().setEditable(true);
        mainViewModeOne.getLeftBoxMuTextField().setEditable(true);
        mainViewModeOne.getLeftBoxFrictionTextField().setEditable(true);

        mainViewModeOne.getLeftSlopeAngleTextField().setEditable(true);
        mainViewModeOne.getRightBoxMassTextField().setEditable(true);


        // Set the non-required fields to uneditable depending on which input type is selected
        switch (mainViewModeOne.getInputTypeComboBox().getSelectedIndex()) {
            case 0:
                mainViewModeOne.getAccelerationTextField().setEditable(false);
                mainViewModeOne.getTensionTextField().setEditable(false);
                mainViewModeOne.getLeftBoxFrictionTextField().setEditable(false);

                // Set default values
                mainViewModeOne.getLeftBoxMassTextField().setText("1");
                mainViewModeOne.getLeftBoxMuTextField().setText("0");
                mainViewModeOne.getLeftSlopeAngleTextField().setText("45");
                mainViewModeOne.getRightBoxMassTextField().setText("1");

                break;

            case 1:
                mainViewModeOne.getAccelerationTextField().setEditable(false);
                mainViewModeOne.getLeftBoxFrictionTextField().setEditable(false);
                mainViewModeOne.getLeftBoxMassTextField().setEditable(false);

                // Set default values
                mainViewModeOne.getTensionTextField().setText("0");
                mainViewModeOne.getLeftBoxMuTextField().setText("0");
                mainViewModeOne.getLeftSlopeAngleTextField().setText("45");
                mainViewModeOne.getRightBoxMassTextField().setText("1");

                break;
        }
    }
}

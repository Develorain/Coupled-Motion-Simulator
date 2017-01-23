package com.ahmad.Controllers;

/** ModeComboBoxController
 * Handles input for the mode combo box
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Models.ModeTwo.SystemModelModeTwo;
import com.ahmad.Startup;
import com.ahmad.Views.ModeOne.MainViewModeOne;
import com.ahmad.Views.ModeTwo.MainViewModeTwo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeComboBoxController implements ActionListener {
    private Startup startup;            // Declare the startup
    private JComboBox modesComboBox;    // Declare the modes combo box

    /** Default Constructor
     * @param startup       a reference to the startup
     * @param modesComboBox a reference to the modes combo box */
    public ModeComboBoxController(Startup startup, JComboBox modesComboBox) {
        this.startup = startup;
        this.modesComboBox = modesComboBox;
    }

    /** Listens for an action to be performed on the combo box
     * @param actionEvent the event created upon interaction */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int currentMode = modesComboBox.getSelectedIndex();

        JPanel contentPane = (JPanel) startup.getJFrame().getContentPane();
        contentPane.removeAll();

        if (currentMode == 0) {
            MainViewModeOne mainViewModeOne = new MainViewModeOne(startup, new SystemModelModeOne());

            contentPane.add(mainViewModeOne.getMainPanel());
        } else if (currentMode == 1) {
            MainViewModeTwo mainViewModeTwo = new MainViewModeTwo(startup, new SystemModelModeTwo());

            contentPane.add(mainViewModeTwo.getMainPanel());
        }

        int state = startup.getJFrame().getExtendedState();
        startup.getJFrame().pack();
        startup.getJFrame().setMinimumSize(startup.getJFrame().getSize());
        startup.getJFrame().setExtendedState(state);

        contentPane.revalidate();
    }
}

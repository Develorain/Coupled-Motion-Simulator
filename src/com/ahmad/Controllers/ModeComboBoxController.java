package com.ahmad.Controllers;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Models.ModeTwo.SystemModelModeTwo;
import com.ahmad.Startup;
import com.ahmad.Tools.Globals;
import com.ahmad.Views.ModeOne.MainViewModeOne;
import com.ahmad.Views.ModeTwo.MainViewModeTwo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeComboBoxController implements ActionListener {
    private JComboBox modesComboBox;

    public ModeComboBoxController(JComboBox modesComboBox) {
        this.modesComboBox = modesComboBox;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Globals.currentMode = modesComboBox.getSelectedIndex() + 1;

        JPanel contentPane = (JPanel) Startup.jFrame.getContentPane();
        contentPane.removeAll();

        if (Globals.currentMode == 1) {
            MainViewModeOne mainViewModeOne = new MainViewModeOne(new SystemModelModeOne());

            contentPane.add(mainViewModeOne.mainPanel);
        } else if (Globals.currentMode == 2) {
            MainViewModeTwo mainViewModeTwo = new MainViewModeTwo(new SystemModelModeTwo());

            contentPane.add(mainViewModeTwo.mainPanel);
        }

        contentPane.revalidate();
    }
}

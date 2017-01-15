package com.ahmad.Controllers;

import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Models.ModeTwo.SystemModelModeTwo;
import com.ahmad.Startup;
import com.ahmad.Views.ModeOne.MainViewModeOne;
import com.ahmad.Views.ModeTwo.MainViewModeTwo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeComboBoxController implements ActionListener {
    private Startup startup;
    private JComboBox modesComboBox;

    public ModeComboBoxController(Startup startup, JComboBox modesComboBox) {
        this.startup = startup;
        this.modesComboBox = modesComboBox;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int currentMode = modesComboBox.getSelectedIndex();

        JPanel contentPane = (JPanel) startup.getJFrame().getContentPane();
        contentPane.removeAll();

        if (currentMode == 0) {
            MainViewModeOne mainViewModeOne = new MainViewModeOne(startup, new SystemModelModeOne());

            contentPane.add(mainViewModeOne.mainPanel);
        } else if (currentMode == 1) {
            MainViewModeTwo mainViewModeTwo = new MainViewModeTwo(startup, new SystemModelModeTwo());

            contentPane.add(mainViewModeTwo.mainPanel);
        }

        startup.getJFrame().pack();
        startup.getJFrame().setMinimumSize(startup.getJFrame().getSize());
        contentPane.revalidate();
    }
}

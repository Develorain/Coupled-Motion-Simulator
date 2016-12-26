package com.ahmad.Controllers;

import com.ahmad.Tools.Globals;

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
    }
}

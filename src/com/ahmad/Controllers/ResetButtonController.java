package com.ahmad.Controllers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetButtonController implements ActionListener {
    private JTextField accelerationTextField;
    private JTextField velocityTextField;
    private JTextField boxAMassTextField;
    private JTextField boxAMuTextField;
    private JTextField boxBMassTextField;
    private JTextField slopeAAngleTextField;

    public ResetButtonController(JTextField accelerationTextField, JTextField velocityTextField,
                                 JTextField boxAMassTextField, JTextField boxAMuTextField,
                                 JTextField boxBMassTextField, JTextField slopeAAngleTextField) {
        this.accelerationTextField = accelerationTextField;
        this.velocityTextField = velocityTextField;
        this.boxAMassTextField = boxAMassTextField;
        this.boxAMuTextField = boxAMuTextField;
        this.boxBMassTextField = boxBMassTextField;
        this.slopeAAngleTextField = slopeAAngleTextField;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        accelerationTextField.setText("");
        velocityTextField.setText("");
        //boxAMassTextField.setText("100");
        //boxAMuTextField.setText("0");
        //boxBMassTextField.setText("1");
        slopeAAngleTextField.setText("45");
    }
}

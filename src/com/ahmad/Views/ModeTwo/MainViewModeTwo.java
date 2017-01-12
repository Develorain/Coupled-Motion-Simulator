package com.ahmad.Views.ModeTwo;

import com.ahmad.Controllers.ModeComboBoxController;
import com.ahmad.Controllers.ModeOne.InputTypeComboBoxControllerModeOne;
import com.ahmad.Controllers.ModeTwo.InputTypeComboBoxControllerModeTwo;
import com.ahmad.Controllers.ModeTwo.LeftAngleTextFieldController;
import com.ahmad.Controllers.ModeTwo.RightAngleTextFieldController;
import com.ahmad.Controllers.ResetButtonController;
import com.ahmad.Controllers.StartButtonController;
import com.ahmad.Models.ModeTwo.SystemModelModeTwo;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.CustomPanel;
import com.ahmad.Tools.GraphicsPainter;
import com.ahmad.Tools.Paintable;
import com.ahmad.Views.View;

import javax.swing.*;
import java.awt.*;

public class MainViewModeTwo implements View, Paintable {
    public CustomPanel systemPanel;

    private SystemModelModeTwo systemModelModeTwo;

    public JPanel mainPanel = new JPanel(new GridBagLayout());

    public JTextField accelerationTextField;
    public JTextField velocityTextField;

    public JTextField leftBoxMassTextField;
    public JTextField leftBoxMuTextField;
    public JTextField leftBoxFrictionTextField;
    public JTextField leftWireTensionTextField;
    public JTextField leftSlopeAngleTextField;

    public JTextField middleBoxMassTextField;
    public JTextField middleBoxMuTextField;
    public JTextField middleBoxFrictionTextField;

    public JTextField rightBoxMassTextField;
    public JTextField rightBoxMuTextField;
    public JTextField rightBoxFrictionTextField;
    public JTextField rightWireTensionTextField;
    public JTextField rightSlopeAngleTextField;

    public JComboBox inputTypeComboBox;
    private JComboBox modesComboBox;
    private JButton startButton;
    private JButton pauseButton;
    private JButton resetButton;

    public MainViewModeTwo(SystemModelModeTwo systemModelModeTwo) {
        this.systemModelModeTwo = systemModelModeTwo;
        systemModelModeTwo.setView(this);

        systemPanel = new CustomPanel(this);
        systemPanel.setPreferredSize(new Dimension(Constants.SIMULATION_WIDTH_PIXELS, Constants.SIMULATION_HEIGHT_PIXELS));

        layoutScreen();
        registerControllers();
    }

    @Override
    public void paint(Graphics graphics) {
        GraphicsPainter.drawSlope(graphics, systemModelModeTwo.leftSlope);
        GraphicsPainter.drawSlope(graphics, systemModelModeTwo.middleSlope);
        GraphicsPainter.drawSlope(graphics, systemModelModeTwo.rightSlope);

        GraphicsPainter.drawPulley(graphics, systemModelModeTwo.leftPulley);
        GraphicsPainter.drawPulley(graphics, systemModelModeTwo.rightPulley);

        GraphicsPainter.drawSlopedBox(graphics, systemModelModeTwo.leftBox, systemModelModeTwo.getLeftSlopeAngle(), false);
        GraphicsPainter.drawSlopedBox(graphics, systemModelModeTwo.middleBox, systemModelModeTwo.getMiddleSlopeAngle(), false);
        GraphicsPainter.drawSlopedBox(graphics, systemModelModeTwo.rightBox, systemModelModeTwo.getRightSlopeAngle(), true);

        GraphicsPainter.drawWire(graphics, systemModelModeTwo.leftWire);
        GraphicsPainter.drawWire(graphics, systemModelModeTwo.rightWire);
    }

    @Override
    public void update() {
        systemPanel.repaint();        // Repaints the simulation area

        updateBoxSystemInfoTable();   // Updates the box system's information table
    }

    @Override
    public void repaint() {
        systemPanel.repaint();
    }

    // Updates all the information tables
    private void updateBoxSystemInfoTable() {
        String accelerationValue = Double.toString(systemModelModeTwo.getAccelerationOfSystem());
        accelerationTextField.setText(accelerationValue);

        String velocityValue = Double.toString(systemModelModeTwo.leftBox.velocity.getR());
        velocityTextField.setText(velocityValue);

        String leftBoxFrictionValue = Double.toString(systemModelModeTwo.leftBox.friction);
        leftBoxFrictionTextField.setText(leftBoxFrictionValue);

        String middleBoxFrictionValue = Double.toString(systemModelModeTwo.middleBox.friction);
        middleBoxFrictionTextField.setText(middleBoxFrictionValue);

        String rightBoxFrictionValue = Double.toString(systemModelModeTwo.rightBox.friction);
        rightBoxFrictionTextField.setText(rightBoxFrictionValue);

        String leftWireTensionValue = Double.toString(systemModelModeTwo.leftWire.tension);
        leftWireTensionTextField.setText(leftWireTensionValue);

        String rightWireTensionValue = Double.toString(systemModelModeTwo.rightWire.tension);
        rightWireTensionTextField.setText(rightWireTensionValue);
    }

    private void registerControllers() {
        StartButtonController sbl = new StartButtonController(this, systemModelModeTwo);
        startButton.addActionListener(sbl);


        LeftAngleTextFieldController latfc = new LeftAngleTextFieldController(leftSlopeAngleTextField, systemModelModeTwo);
        leftSlopeAngleTextField.addKeyListener(latfc);

        RightAngleTextFieldController ratfc = new RightAngleTextFieldController(rightSlopeAngleTextField, systemModelModeTwo);
        rightSlopeAngleTextField.addKeyListener(ratfc);


        ResetButtonController rbc = new ResetButtonController(accelerationTextField, velocityTextField,
                leftBoxMassTextField, leftBoxMuTextField, rightBoxMassTextField, leftSlopeAngleTextField);
        resetButton.addActionListener(rbc);


        ModeComboBoxController mcbc = new ModeComboBoxController(modesComboBox);
        modesComboBox.addActionListener(mcbc);

        InputTypeComboBoxControllerModeTwo itcbc = new InputTypeComboBoxControllerModeTwo(this);
        inputTypeComboBox.addActionListener(itcbc);
    }

    private void layoutScreen() {
        GridBagConstraints gc = new GridBagConstraints();

        // Creates and adds simulation view to the main JPanel
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 9;
        gc.weighty = 100;
        mainPanel.add(systemPanel, gc);

        createBoxSystemTable(gc);

        createLeftBoxTable(gc);
        createLeftWireTable(gc);
        createLeftSlopeTable(gc);

        createMiddleBoxTable(gc);

        createRightBoxTable(gc);
        createRightWireTable(gc);
        createRightSlopeTable(gc);

        createButtonTable(gc);
    }

    private void createRightSlopeTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;

        JLabel rightSlopeTitleLabel = new JLabel("Right Slope");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 6;
        gc.gridy = 7;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        mainPanel.add(rightSlopeTitleLabel, gc);

        JLabel rightSlopeAngleLabel = new JLabel("Angle ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 6;
        gc.gridy = 8;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        mainPanel.add(rightSlopeAngleLabel, gc);

        rightSlopeAngleTextField = new JTextField(10);
        rightSlopeAngleTextField.setText("45");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 7;
        gc.gridy = 8;
        mainPanel.add(rightSlopeAngleTextField, gc);
    }

    private void createRightBoxTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel rightBoxTitleLabel = new JLabel("Right Box");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 6;
        gc.gridy = 1;
        gc.gridwidth = 2;
        mainPanel.add(rightBoxTitleLabel, gc);

        JLabel rightBoxMassLabel = new JLabel("Mass ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 6;
        gc.gridy = 2;
        gc.gridwidth = 1;
        mainPanel.add(rightBoxMassLabel, gc);

        JLabel rightBoxMuLabel = new JLabel("Mu ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 6;
        gc.gridy = 3;
        gc.gridwidth = 1;
        mainPanel.add(rightBoxMuLabel, gc);

        JLabel rightBoxFrictionLabel = new JLabel("Friction ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 6;
        gc.gridy = 4;
        gc.gridwidth = 1;
        mainPanel.add(rightBoxFrictionLabel, gc);

        rightBoxMassTextField = new JTextField("1", 10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 7;
        gc.gridy = 2;
        mainPanel.add(rightBoxMassTextField, gc);

        rightBoxMuTextField = new JTextField(10);
        rightBoxMuTextField.setText("0");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 7;
        gc.gridy = 3;
        mainPanel.add(rightBoxMuTextField, gc);

        rightBoxFrictionTextField = new JTextField(10);
        rightBoxFrictionTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 7;
        gc.gridy = 4;
        mainPanel.add(rightBoxFrictionTextField, gc);
    }

    private void createButtonTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weighty = 1;

        String[] inputTypeList = new String[]{
                "Solve for Acceleration and All Tensions",
                "Placeholder 2 Placeholder 2 Placeholder 2"
        };
        inputTypeComboBox = new JComboBox<>(inputTypeList);
        gc.gridx = 8;
        gc.gridy = 1;
        mainPanel.add(inputTypeComboBox, gc);

        String[] modesList = new String[]{"Two Box System", "Three Box System"};
        modesComboBox = new JComboBox<>(modesList); //todo: if doesn't work at school, try removing <>
        modesComboBox.setSelectedIndex(1);
        gc.gridx = 8;
        gc.gridy = 2;
        mainPanel.add(modesComboBox, gc);

        startButton = new JButton("Start Simulation");
        gc.gridx = 8;
        gc.gridy = 3;
        mainPanel.add(startButton, gc);

        pauseButton = new JButton("Pause Simulation");
        gc.gridx = 8;
        gc.gridy = 4;
        mainPanel.add(pauseButton, gc);

        resetButton = new JButton("Reset Simulation");
        gc.gridx = 8;
        gc.gridy = 5;
        mainPanel.add(resetButton, gc);
    }

    private void createMiddleBoxTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel middleBoxTitleLabel = new JLabel("Middle Box");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 4;
        gc.gridy = 1;
        gc.gridwidth = 2;
        mainPanel.add(middleBoxTitleLabel, gc);

        JLabel middleBoxMassLabel = new JLabel("Mass ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 4;
        gc.gridy = 2;
        gc.gridwidth = 1;
        mainPanel.add(middleBoxMassLabel, gc);

        JLabel middleBoxMuLabel = new JLabel("Mu ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 4;
        gc.gridy = 3;
        gc.gridwidth = 1;
        mainPanel.add(middleBoxMuLabel, gc);

        JLabel middleBoxFrictionLabel = new JLabel("Friction ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 4;
        gc.gridy = 4;
        gc.gridwidth = 1;
        mainPanel.add(middleBoxFrictionLabel, gc);

        middleBoxMassTextField = new JTextField("1", 10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 5;
        gc.gridy = 2;
        mainPanel.add(middleBoxMassTextField, gc);

        middleBoxMuTextField = new JTextField(10);
        middleBoxMuTextField.setText("0");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 5;
        gc.gridy = 3;
        mainPanel.add(middleBoxMuTextField, gc);

        middleBoxFrictionTextField = new JTextField(10);
        middleBoxFrictionTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 5;
        gc.gridy = 4;
        mainPanel.add(middleBoxFrictionTextField, gc);
    }

    private void createLeftBoxTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel leftBoxTitleLabel = new JLabel("Left Box");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 2;
        gc.gridy = 1;
        gc.gridwidth = 2;
        mainPanel.add(leftBoxTitleLabel, gc);

        JLabel leftBoxMassLabel = new JLabel("Mass ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 2;
        gc.gridwidth = 1;
        mainPanel.add(leftBoxMassLabel, gc);

        JLabel leftBoxMuLabel = new JLabel("Mu ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 3;
        mainPanel.add(leftBoxMuLabel, gc);

        JLabel leftBoxFrictionLabel = new JLabel("Friction ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 4;
        mainPanel.add(leftBoxFrictionLabel, gc);

        leftBoxMassTextField = new JTextField(10);
        leftBoxMassTextField.setText("1");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 2;
        mainPanel.add(leftBoxMassTextField, gc);

        leftBoxMuTextField = new JTextField(10);
        leftBoxMuTextField.setText("0");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 3;
        mainPanel.add(leftBoxMuTextField, gc);

        leftBoxFrictionTextField = new JTextField(10);
        leftBoxFrictionTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 4;
        mainPanel.add(leftBoxFrictionTextField, gc);
    }

    private void createLeftWireTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel leftWireTitleLabel = new JLabel("Left Wire");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 2;
        gc.gridy = 5;
        gc.gridwidth = 2;
        mainPanel.add(leftWireTitleLabel, gc);

        JLabel leftWireTensionLabel = new JLabel("Tension ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 6;
        gc.gridwidth = 1;
        mainPanel.add(leftWireTensionLabel, gc);

        leftWireTensionTextField = new JTextField(10);
        leftWireTensionTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 6;
        mainPanel.add(leftWireTensionTextField, gc);
    }

    private void createRightWireTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel rightWireTitleLabel = new JLabel("Right Wire");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 6;
        gc.gridy = 5;
        gc.gridwidth = 2;
        mainPanel.add(rightWireTitleLabel, gc);

        JLabel rightWireTensionLabel = new JLabel("Tension ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 6;
        gc.gridy = 6;
        gc.gridwidth = 1;
        mainPanel.add(rightWireTensionLabel, gc);

        rightWireTensionTextField = new JTextField(10);
        rightWireTensionTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 7;
        gc.gridy = 6;
        mainPanel.add(rightWireTensionTextField, gc);
    }

    private void createLeftSlopeTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;

        JLabel leftSlopeTitleLabel = new JLabel("Left Slope");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 2;
        gc.gridy = 7;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        mainPanel.add(leftSlopeTitleLabel, gc);

        JLabel leftSlopeAngleLabel = new JLabel("Angle ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 8;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        mainPanel.add(leftSlopeAngleLabel, gc);

        leftSlopeAngleTextField = new JTextField(10);
        leftSlopeAngleTextField.setText("45");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 8;
        mainPanel.add(leftSlopeAngleTextField, gc);
    }

    private void createBoxSystemTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel boxSystemLabel = new JLabel("Box System");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        mainPanel.add(boxSystemLabel, gc);

        JLabel accelerationLabel = new JLabel("Acceleration ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 1;
        mainPanel.add(accelerationLabel, gc);

        JLabel velocityLabel = new JLabel("Velocity ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 3;
        mainPanel.add(velocityLabel, gc);

        accelerationTextField = new JTextField(10);
        accelerationTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 2;
        mainPanel.add(accelerationTextField, gc);

        velocityTextField = new JTextField(10);
        velocityTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 3;
        mainPanel.add(velocityTextField, gc);
    }
}
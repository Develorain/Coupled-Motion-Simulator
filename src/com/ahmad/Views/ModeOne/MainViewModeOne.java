package com.ahmad.Views.ModeOne;

/** MainViewModeOne
 * The main Graphics User Interface for mode one
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Controllers.ModeComboBoxController;
import com.ahmad.Controllers.ModeOne.AngleTextFieldControllerModeOne;
import com.ahmad.Controllers.ModeOne.InputTypeComboBoxControllerModeOne;
import com.ahmad.Controllers.ModeOne.StartButtonControllerModeOne;
import com.ahmad.Controllers.ResetButtonController;
import com.ahmad.Models.ModeOne.SystemModelModeOne;
import com.ahmad.Startup;
import com.ahmad.Tools.Constants;
import com.ahmad.Tools.CustomPanel;
import com.ahmad.Tools.GraphicsPainter;
import com.ahmad.Tools.Paintable;
import com.ahmad.Views.View;

import javax.swing.*;
import java.awt.*;

public class MainViewModeOne implements View, Paintable {
    private CustomPanel systemPanel;                            // Declare the system panel
    private SystemModelModeOne systemModelModeOne;              // Declare the system model

    private JPanel mainPanel = new JPanel(new GridBagLayout()); // Declare the main panel
    private Startup startup;                                    // Declare the startup

    private JTextField accelerationTextField;       // Declare the acceleration text field
    private JTextField velocityTextField;           // Declare the velocity text field
    private JTextField leftBoxFrictionTextField;    // Declare the left box friction text field
    private JTextField tensionTextField;            // Declare the tension text field

    private JTextField leftBoxMassTextField;        // Declare the left box mass text field
    private JTextField leftBoxMuTextField;          // Declare the left box mu text field

    private JTextField rightBoxMassTextField;       // Declare the right box mass text field

    private JTextField leftSlopeAngleTextField;     // Declare the left slope angle text field

    private JComboBox inputTypeComboBox;            // Declare the input type combo box
    private JComboBox modesComboBox;                // Declare the modes combo box
    private JButton startButton;                    // Declare the start button
    private JButton resetButton;                    // Declare the reset button

    /** Default Constructor
     * @param startup            a reference to the startup
     * @param systemModelModeOne a reference to the system model */
    public MainViewModeOne(Startup startup, SystemModelModeOne systemModelModeOne) {
        this.systemModelModeOne = systemModelModeOne;
        this.startup = startup;

        systemModelModeOne.setView(this);

        systemPanel = new CustomPanel(this);
        systemPanel.setPreferredSize(new Dimension(Constants.SIMULATION_WIDTH_PIXELS, Constants.SIMULATION_HEIGHT_PIXELS));

        layoutScreen();
        registerControllers();
    }

    /** Paint method to draw custom graphics
     * @param graphics a reference to graphics instance */
    @Override
    public void paint(Graphics graphics) {
        GraphicsPainter.drawSlope(graphics, systemModelModeOne.getSlope());
        GraphicsPainter.drawPulley(graphics, systemModelModeOne.getPulley());

        GraphicsPainter.drawSlopedBox(graphics, systemModelModeOne.getSlopedBox(), systemModelModeOne.getSlopeAngle(), false);
        GraphicsPainter.drawDanglingBox(graphics, systemModelModeOne.getDanglingBox());
        GraphicsPainter.drawWire(graphics, systemModelModeOne.getWire());

        GraphicsPainter.drawVerticalLine(graphics, systemModelModeOne.getSlope());
    }

    /** Gets data from the model and updates the view */
    @Override
    public void update() {
        systemPanel.repaint();       // Repaints the simulation area

        updateBoxSystemInfoTable();  // Updates the box system's information table
    }

    /** Repaints the view */
    @Override
    public void repaint() {
        systemPanel.repaint();
    }

    /** Updates all the information tables on the screen */
    private void updateBoxSystemInfoTable() {
        String accelerationValue = Double.toString(systemModelModeOne.getAccelerationOfSystem());
        accelerationTextField.setText(accelerationValue);

        String velocityValue = Double.toString(systemModelModeOne.getSlopedBox().getVelocity().getX() > 0
                ? systemModelModeOne.getSlopedBox().getVelocity().getR()
                : -systemModelModeOne.getSlopedBox().getVelocity().getR());

        velocityTextField.setText(velocityValue);

        String tensionValue = Double.toString(systemModelModeOne.getWire().getTension());
        tensionTextField.setText(tensionValue);

        String leftBoxMassValue = Double.toString(systemModelModeOne.getSlopedBox().getMass());
        leftBoxMassTextField.setText(leftBoxMassValue);

        String leftBoxMuValue = Double.toString(systemModelModeOne.getSlopedBox().getMu());
        leftBoxMuTextField.setText(leftBoxMuValue);

        String leftBoxFrictionValue = Double.toString(systemModelModeOne.getSlopedBox().getFriction());
        leftBoxFrictionTextField.setText(leftBoxFrictionValue);

        String leftSlopeAngleValue = Double.toString(systemModelModeOne.getSlopeAngle());
        leftSlopeAngleTextField.setText(leftSlopeAngleValue);

        String rightBoxMassValue = Double.toString(systemModelModeOne.getDanglingBox().getMass());
        rightBoxMassTextField.setText(rightBoxMassValue);
    }

    /** Registers all the view's controllers */
    private void registerControllers() {
        StartButtonControllerModeOne sbc = new StartButtonControllerModeOne(this, systemModelModeOne);
        startButton.addActionListener(sbc);


        AngleTextFieldControllerModeOne atfc = new AngleTextFieldControllerModeOne(leftSlopeAngleTextField, systemModelModeOne);
        leftSlopeAngleTextField.addKeyListener(atfc);


        ResetButtonController rbc = new ResetButtonController(startup, modesComboBox);
        resetButton.addActionListener(rbc);


        ModeComboBoxController mcbc = new ModeComboBoxController(startup, modesComboBox);
        modesComboBox.addActionListener(mcbc);

        InputTypeComboBoxControllerModeOne itcbc = new InputTypeComboBoxControllerModeOne(this);
        inputTypeComboBox.addActionListener(itcbc);
    }

    /** Lays out the components of the screen */
    private void layoutScreen() {
        GridBagConstraints gc = new GridBagConstraints();

        // Creates and adds simulation view to the main JPanel
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 7;
        gc.weighty = 100;
        mainPanel.add(systemPanel, gc);

        createBoxSystemTable(gc);

        createLeftBoxTable(gc);
        createLeftSlopeTable(gc);

        createRightBoxTable(gc);

        createButtonTable(gc);
    }

    /** Creates the button table */
    private void createButtonTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        gc.weighty = 1;

        String[] modesList = new String[]{"Two Box System", "Three Box System"};
        modesComboBox = new JComboBox<>(modesList);
        gc.gridx = 6;
        gc.gridy = 1;
        mainPanel.add(modesComboBox, gc);

        String[] inputTypeList = new String[]{
                "Solve for Friction, Acceleration, and Tension",
                "Solve for Acceleration, Left Mass, and Friction"
        };
        inputTypeComboBox = new JComboBox<>(inputTypeList);
        gc.gridx = 6;
        gc.gridy = 2;
        mainPanel.add(inputTypeComboBox, gc);

        startButton = new JButton("Start Simulation");
        gc.gridx = 6;
        gc.gridy = 3;
        mainPanel.add(startButton, gc);

        resetButton = new JButton("Reset Simulation");
        gc.gridx = 6;
        gc.gridy = 4;
        mainPanel.add(resetButton, gc);
    }

    /** Creates the right box table */
    private void createRightBoxTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;
        gc.gridheight = 1;
        gc.weightx = 1;
        gc.weighty = 1;

        JLabel rightBoxTitleLabel = new JLabel("Right Box");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 4;
        gc.gridy = 1;
        gc.gridwidth = 2;
        mainPanel.add(rightBoxTitleLabel, gc);

        JLabel rightBoxMassLabel = new JLabel("Mass ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 4;
        gc.gridy = 2;
        gc.gridwidth = 1;
        mainPanel.add(rightBoxMassLabel, gc);

        rightBoxMassTextField = new JTextField("1", 10);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 5;
        gc.gridy = 2;
        mainPanel.add(rightBoxMassTextField, gc);
    }

    /** Creates the left box table */
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

    /** Creates the left slope table */
    private void createLeftSlopeTable(GridBagConstraints gc) {
        gc.fill = GridBagConstraints.NONE;

        JLabel leftSlopeTitleLabel = new JLabel("Left Slope");
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 2;
        gc.gridy = 5;
        gc.gridwidth = 2;
        gc.gridheight = 1;
        mainPanel.add(leftSlopeTitleLabel, gc);

        JLabel leftSlopeAngleLabel = new JLabel("Angle ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 2;
        gc.gridy = 6;
        gc.gridwidth = 1;
        gc.gridheight = 1;
        mainPanel.add(leftSlopeAngleLabel, gc);

        leftSlopeAngleTextField = new JTextField(10);
        leftSlopeAngleTextField.setText("45");
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 3;
        gc.gridy = 6;
        mainPanel.add(leftSlopeAngleTextField, gc);
    }

    /** Creates the box system table */
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

        JLabel tensionLabel = new JLabel("Tension ");
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 4;
        mainPanel.add(tensionLabel, gc);

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

        tensionTextField = new JTextField(10);
        tensionTextField.setEditable(false);
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 4;
        mainPanel.add(tensionTextField, gc);
    }

    /** Returns the main panel
     * @return the main panel */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /** Returns the acceleration text field
     * @return the acceleration text field */
    public JTextField getAccelerationTextField() {
        return accelerationTextField;
    }

    /** Returns the velocity text field
     * @return the velocity text field */
    public JTextField getVelocityTextField() {
        return velocityTextField;
    }

    /** Returns the left box friction text field
     * @return the left box friction text field */
    public JTextField getLeftBoxFrictionTextField() {
        return leftBoxFrictionTextField;
    }

    /** Returns the tension text field
     * @return the tension text field */
    public JTextField getTensionTextField() {
        return tensionTextField;
    }

    /** Returns the left box mass text field
     * @return the left box mass text field */
    public JTextField getLeftBoxMassTextField() {
        return leftBoxMassTextField;
    }

    /** Returns the left box mu text field
     * @return the left box mu text field */
    public JTextField getLeftBoxMuTextField() {
        return leftBoxMuTextField;
    }

    /** Returns the right box mass text field
     * @return the right box mass text field */
    public JTextField getRightBoxMassTextField() {
        return rightBoxMassTextField;
    }

    /** Returns the left slope angle text field
     * @return the left slope angle text field */
    public JTextField getLeftSlopeAngleTextField() {
        return leftSlopeAngleTextField;
    }

    /** Returns the input type combo box
     * @return the input type combo box */
    public JComboBox getInputTypeComboBox() {
        return inputTypeComboBox;
    }

    /** Returns the start button
     * @return the start button */
    public JButton getStartButton() {
        return startButton;
    }
}
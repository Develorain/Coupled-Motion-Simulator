package com.ahmad.Controllers;

/** StartButtonController
 * Handles input for the start button
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Models.SystemModel;
import com.ahmad.Tools.Constants;
import com.ahmad.Views.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class StartButtonController implements ActionListener {
    private View view;                // Declare the view
    private SystemModel systemModel;  // Declare the system model

    /** Default Constructor
     * @param view        a reference to the view instance
     * @param systemModel a reference to the system model */
    public StartButtonController(View view, SystemModel systemModel) {
        this.view = view;
        this.systemModel = systemModel;
    }

    /** Listens for an action to be performed on the start button
     * @param actionEvent the event created upon interaction */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        systemModel.setActive();

        systemModel.takeInputAndInitializeConstantValues(view);

        TimerTask timerTask = new TimerTask() {
            public void run() {
                systemModel.iterate();
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000 / Constants.UPDATES_PER_SECOND);
    }
}

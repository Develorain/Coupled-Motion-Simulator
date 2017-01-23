package com.ahmad.Models;

/** SystemModel
 * General implementable SystemModel interface
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Views.View;

public interface SystemModel {
    /** Sets the simulation to active */
    void setActive();

    /** Takes input from the view and calls the appropriate methods to initialize constant values
     * @param mainView a reference to the main view */
    void takeInputAndInitializeConstantValues(View mainView);

    /** Iterates the physics in the system */
    void iterate();
}

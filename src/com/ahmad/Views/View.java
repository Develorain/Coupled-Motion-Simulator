package com.ahmad.Views;

/** View
 * General implementable View interface in the MVC pattern
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

public interface View {
    /** Gets data from the model and updates the view */
    void update();

    /** Repaints the view */
    void repaint();
}

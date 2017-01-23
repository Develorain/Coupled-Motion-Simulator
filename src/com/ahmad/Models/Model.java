package com.ahmad.Models;

/** Model
 * General inheritable model class in the MVC pattern
 * @since January 18, 2017
 * @author Ahmad Gharib
 */

import com.ahmad.Views.View;

public class Model {
    private View view;  // Declare the view

    /** Sets the view of the model
     * @param view the view to be set */
    public void setView(View view) {
        this.view = view;
    }

    /** Notifies the view */
    protected void updateView() {
        view.update();
    }

    /** Repaints the view */
    protected void repaintView() {
        view.repaint();
    }
}

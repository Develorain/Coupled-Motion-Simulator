package com.ahmad.Models;

import com.ahmad.Views.View;

public class Model {
    private View view;

    public void setView(View view) {
        this.view = view;
    }

    public void updateView() {
        view.update();
    }

    public void repaintView() {
        view.repaint();
    }
}

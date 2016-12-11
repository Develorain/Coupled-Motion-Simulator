package com.ahmad.Models;

import com.ahmad.Views.View;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<View> views = new ArrayList<>();

    public void attach(View view) {
        views.add(view);
    }

    public void notifyViews() {
        for (View view : views) {
            view.update(this);
        }
    }
}

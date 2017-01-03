package com.ahmad.Models;

import com.ahmad.Views.View;

public interface SystemModel {
    void takeInputAndInitializeConstantValues(View mainView);

    void iterate();
}

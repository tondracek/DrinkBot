package com.thomasthedeveloper.drinkbot;

import android.content.Context;
import android.text.Layout;

import androidx.constraintlayout.widget.ConstraintLayout;

public interface MVPView {
    void init(Context context);
    void updateUI(MVPModel mvpModel);
}

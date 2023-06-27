package com.thomasthedeveloper.drinkbot;

import android.content.Context;
import android.view.ViewGroup;

public interface MVPView {
    void init(Context context);
    void updateUI(MVPModel mvpModel);
}

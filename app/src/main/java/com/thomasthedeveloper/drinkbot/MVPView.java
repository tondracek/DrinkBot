package com.thomasthedeveloper.drinkbot;

import android.content.Context;

public interface MVPView {
    void init(Context context);
    void updateUI(MVPModel mvpModel);
}

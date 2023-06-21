package com.thomasthedeveloper.drinkbot.amount_adding;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.view.inputmethod.InputMethodManager;

import com.thomasthedeveloper.drinkbot.MainActivity;
import com.thomasthedeveloper.drinkbot.R;
import com.thomasthedeveloper.drinkbot.counter.Counter;

public class AmountAdding {
    private final AmountAddingView view;

    public AmountAdding(AmountAddingView amountAddingView, Counter counter, MainActivity mainActivity) {
        this.view = amountAddingView;

        amountAddingView.getAddButton().setOnClickListener(view -> {
            int amount = amountAddingView.getAmount();
            counter.addToTotal(amount);
        });

        // CLOSING KEYBOARD AFTER FOCUS LOST
        mainActivity.findViewById(R.id.mainLayout).setOnClickListener(view -> {
            InputMethodManager manager = (InputMethodManager) mainActivity.getApplicationContext()
                    .getSystemService(INPUT_METHOD_SERVICE);
            killFocus(manager);
        });
    }

    public void killFocus(InputMethodManager manager) {
        view.killFocus(manager);
    }
}

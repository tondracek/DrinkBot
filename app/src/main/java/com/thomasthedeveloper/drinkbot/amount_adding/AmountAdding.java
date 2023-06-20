package com.thomasthedeveloper.drinkbot.amount_adding;

import android.view.inputmethod.InputMethodManager;

import com.thomasthedeveloper.drinkbot.counter.Counter;

public class AmountAdding {
    private final AmountAddingView view;

    public AmountAdding(AmountAddingView amountAddingView, Counter counter) {
        this.view = amountAddingView;

        amountAddingView.getAddButton().setOnClickListener(view -> {
            int amount = amountAddingView.getAmount();
            counter.addToTotal(amount);
        });
    }

    public void killFocus(InputMethodManager manager) {
        view.killFocus(manager);
    }
}

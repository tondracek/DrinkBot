package com.thomasthedeveloper.drinkbot.amount_adding;

import android.content.Context;
import android.view.ViewGroup;

import com.thomasthedeveloper.drinkbot.MVPPresenter;
import com.thomasthedeveloper.drinkbot.counter.Counter;

public class AmountAdding implements MVPPresenter {
    private final AmountAddingView addingView;
    private final AmountAddingModel addingModel;

    public AmountAdding(AmountAddingView amountAddingView, Counter counter, Context context) {
        this.addingView = amountAddingView;

        int[] range = new int[]{50, 100, 150, 200, 250, 300, 350, 400, 450, 500};
        addingModel = new AmountAddingModel(context, range, counter);

        updateUI();
    }

    @Override
    public void updateUI() {
        addingView.updateUI(addingModel);
    }

    @Override
    public ViewGroup getView() {
        return addingView;
    }
}

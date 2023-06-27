package com.thomasthedeveloper.drinkbot.drinking_history;

import android.content.Context;

import com.thomasthedeveloper.drinkbot.MVPPresenter;

public class DrinkingHistory implements MVPPresenter {
    private final DrinkingHistoryModel historyModel;
    private final DrinkingHistoryView historyView;

    public DrinkingHistory(DrinkingHistoryView historyView, Context context) {
        this.historyView = historyView;
        historyModel = DrinkingHistoryModel.loadFromMemory(context);

        updateUI();
    }

    @Override
    public void updateUI() {
        historyView.updateUI(historyModel);
    }

    public DrinkingHistoryView getView() {
        return historyView;
    }

    public void saveToMemory(Context context) {
        historyModel.saveToMemory(context);
    }

    public int getSize() {
        return historyModel.getHistory().size();
    }
}

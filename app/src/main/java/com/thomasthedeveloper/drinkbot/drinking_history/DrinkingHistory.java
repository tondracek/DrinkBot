package com.thomasthedeveloper.drinkbot.drinking_history;

import android.content.Context;

public class DrinkingHistory {
    private final DrinkingHistoryModel historyModel;
    private final DrinkingHistoryView historyView;

    public DrinkingHistory(DrinkingHistoryView historyView, Context context) {
        this.historyView = historyView;
        historyModel = DrinkingHistoryModel.loadFromMemory(context);

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

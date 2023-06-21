package com.thomasthedeveloper.drinkbot.drinking_history;

import android.content.Context;
import android.widget.Toast;

import com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit.DrinkingHistoryUnitModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DrinkingHistory {
    private final DrinkingHistoryModel historyModel;
    private final DrinkingHistoryView historyView;

    public DrinkingHistory(DrinkingHistoryView historyView, Context context) {
        this.historyView = historyView;
        historyModel = DrinkingHistoryModel.loadFromMemory(context);
    }

    public void add(DrinkingHistoryUnitModel unit) {
        historyModel.add(unit);
        historyView.updateUI(historyModel);
    }

    public DrinkingHistoryView getView() {
        return historyView;
    }

    public void saveToMemory(Context context) {
        historyModel.saveToMemory(context);
    }
}

package com.thomasthedeveloper.drinkbot.drinking_history;

import android.content.Context;
import android.widget.Toast;

import com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit.DrinkingHistoryUnitModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DrinkingHistory {
    private DrinkingHistoryModel historyModel;
    private final DrinkingHistoryView historyView;

    public DrinkingHistory(DrinkingHistoryView historyView) {
        this.historyView = historyView;
        historyModel = new DrinkingHistoryModel();
    }

    public void add(DrinkingHistoryUnitModel unit) {
        historyModel.add(unit);
        historyView.updateUI(historyModel);
    }

    public void loadFromMemory(Context context) {
        try {
            FileInputStream fileInputStream = context.openFileInput("history.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            historyModel = (DrinkingHistoryModel) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            Toast.makeText(context, "Couldn't load the history", Toast.LENGTH_SHORT).show();
        }
        historyView.updateUI(historyModel);
    }

    public void saveToMemory(Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("history.bin", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(historyModel);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            Toast.makeText(context, "Couldn't save the history", Toast.LENGTH_SHORT).show();
        }
    }
}

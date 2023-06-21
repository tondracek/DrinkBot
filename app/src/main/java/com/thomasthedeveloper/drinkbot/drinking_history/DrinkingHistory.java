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

    public DrinkingHistoryView getView() {
        return historyView;
    }

    public void loadFromMemory(Context context) {
        File saveFile = new File(context.getFilesDir(), "history.bin");
        try {
            FileInputStream fileInputStream = new FileInputStream(saveFile);
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
        File saveFile = new File(context.getFilesDir(), "history.bin");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(historyModel);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            Toast.makeText(context, "Couldn't save the history", Toast.LENGTH_SHORT).show();
        }
    }
}

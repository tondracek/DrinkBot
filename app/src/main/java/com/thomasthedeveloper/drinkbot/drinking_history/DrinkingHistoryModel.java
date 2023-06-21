package com.thomasthedeveloper.drinkbot.drinking_history;

import android.content.Context;
import android.widget.Toast;

import com.thomasthedeveloper.drinkbot.MVPModel;
import com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit.DrinkingHistoryUnitModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class DrinkingHistoryModel implements MVPModel, Serializable {
    private final SortedSet<DrinkingHistoryUnitModel> history = new TreeSet<>(Comparator.reverseOrder());

    private DrinkingHistoryModel() {
    }

    public void add(DrinkingHistoryUnitModel unit) {
        if (unit != null)
            history.add(unit);
    }

    public Collection<DrinkingHistoryUnitModel> getHistory() {
        return history;
    }

    public static DrinkingHistoryModel loadFromMemory(Context context) {
        File saveFile = new File(context.getFilesDir(), "history.bin");
        try {
            FileInputStream fileInputStream = new FileInputStream(saveFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            DrinkingHistoryModel historyModel = (DrinkingHistoryModel) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
            return historyModel;
        } catch (Exception e) {
            Toast.makeText(context, "Couldn't load the history", Toast.LENGTH_SHORT).show();
        }
        return new DrinkingHistoryModel();
    }

    public void saveToMemory(Context context) {
        File saveFile = new File(context.getFilesDir(), "history.bin");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(this);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            Toast.makeText(context, "Couldn't save the history", Toast.LENGTH_SHORT).show();
        }
    }
}

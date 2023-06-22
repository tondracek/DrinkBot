package com.thomasthedeveloper.drinkbot.drinking_history;

import android.content.Context;
import android.widget.Toast;

import com.thomasthedeveloper.drinkbot.MVPModel;
import com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit.DrinkingHistoryUnitModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class DrinkingHistoryModel implements MVPModel, Serializable {
    private final TreeSet<DrinkingHistoryUnitModel> history = new TreeSet<>(Comparator.reverseOrder());

    private DrinkingHistoryModel() {
    }

    public static DrinkingHistoryModel loadFromMemory(Context context) {
        File saveFile = new File(context.getFilesDir(), "history.bin");
        try {
            if (saveFile.createNewFile()) {
                return new DrinkingHistoryModel();
            }
            FileInputStream fileInputStream = new FileInputStream(saveFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            DrinkingHistoryModel historyModel = (DrinkingHistoryModel) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            return historyModel;
        } catch (ClassNotFoundException | IOException e) {
            if (saveFile.exists() && !saveFile.delete()) {
                throw new RuntimeException(e);
            }
        }
        return new DrinkingHistoryModel();
    }

    public void add(DrinkingHistoryUnitModel unit, Context context) {
        if (unit != null) {
            history.add(unit);
            saveToMemory(context);
        }
    }

    public Collection<DrinkingHistoryUnitModel> getHistory() {
        return history;
    }

    public void saveToMemory(Context context) {
        File saveFile = new File(context.getFilesDir(), "history.bin");
        try {
            if (!saveFile.exists() && !saveFile.createNewFile())
                Toast.makeText(context, "Couldn't save the history", Toast.LENGTH_SHORT).show();

            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(this);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            Toast.makeText(context, "Couldn't save the history", Toast.LENGTH_SHORT).show();
        }
    }

    public float successRate() {
        if (history.size() == 0) {
            return 0;
        }
        float successfulDays = history.stream().filter(DrinkingHistoryUnitModel::isAccomplished).count();

        return successfulDays / history.size();
    }

    public int consecutiveCount() {
        int count = 0;
        for (DrinkingHistoryUnitModel unitModel : history) {
            if (unitModel.isAccomplished()) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}

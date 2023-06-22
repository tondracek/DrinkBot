package com.thomasthedeveloper.drinkbot.counter;

import android.content.Context;
import android.widget.Toast;

import com.thomasthedeveloper.drinkbot.MVPModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class CounterModel implements Serializable, MVPModel {
    private final ArrayList<DrinkRecord> history = new ArrayList<>();
    private LocalDate date;
    private int total;
    private int goal;

    public CounterModel() {
        total = 0;
        goal = 2000;
        date = LocalDate.now();
    }

    public static CounterModel loadFromMemory(Context context) {
        File saveFile = new File(context.getFilesDir(), "counter.bin");
        try {
            if (saveFile.createNewFile()) {
                return new CounterModel();
            }
            FileInputStream fileInputStream = new FileInputStream(saveFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            CounterModel loadedModel = (CounterModel) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            return loadedModel;
        } catch (IOException | ClassNotFoundException e) {
            if (saveFile.exists() && !saveFile.delete()) {
                throw new RuntimeException(e);
            }
        }

        return new CounterModel();
    }

    public void addToTotal(int add_amount) {
        history.add(new DrinkRecord(total, goal));
        setTotal(total + add_amount);
    }

    public void removeLastAdd() {
        if (history.size() == 0) {
            return;
        }

        DrinkRecord last = history.remove(history.size() - 1);
        if (last != null) {
            setTotal(last.getTotal());
            setGoal(last.getGoal());
        }
    }

    public boolean isActual() {
        LocalDate actualDate = LocalDate.now();

        return date.compareTo(actualDate) == 0;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int new_goal) {
        goal = new_goal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int new_total) {
        total = new_total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void saveToMemory(Context context) {
        File saveFile = new File(context.getFilesDir(), "counter.bin");
        try {
            if (!saveFile.exists() && !saveFile.createNewFile())
                Toast.makeText(context, "Unable to save backup", Toast.LENGTH_SHORT).show();

            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(this);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            Toast.makeText(context, "Unable to save backup", Toast.LENGTH_SHORT).show();
        }
    }

    private static class DrinkRecord implements Serializable {
        private final int total;
        private final int goal;

        public DrinkRecord(int total, int goal) {
            this.total = total;
            this.goal = goal;
        }

        public int getTotal() {
            return total;
        }

        public int getGoal() {
            return goal;
        }
    }
}
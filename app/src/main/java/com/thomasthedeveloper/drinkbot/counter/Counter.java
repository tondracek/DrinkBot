package com.thomasthedeveloper.drinkbot.counter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class handles communication between counterModel and counterView
 * and loads data saved in memory
 */
public class Counter {
    private CounterModel counterModel;
    private final CounterView counterView;

    @SuppressLint("ClickableViewAccessibility")
    public Counter(CounterView counterView) {
        this.counterView = counterView;
        counterModel = new CounterModel();
        counterView.updateUI(counterModel);

        counterView.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                goalSetPopUp(view);
                counterView.updateUI(counterModel);
            }

            return true;
        });
    }

    public void addToTotal(int addAmount) {
        counterModel.addToTotal(addAmount);
        counterView.updateUI(counterModel);
    }

    private void goalSetPopUp(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Choose a new goal");

        EditText input = new EditText(view.getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        builder.setPositiveButton("Set", (dialogInterface, i) -> {
            int newGoal = Integer.parseInt(String.valueOf(input.getText()));
            counterModel.setGoal(newGoal);
        });
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel());
        builder.show();
    }

    /**
     * @param context of application
     * @return outdated {@link CounterModel} or null if it is not outdated
     */
    public CounterModel loadFromMemory(Context context) {
        CounterModel loadedModel = loadModelFromFile(context);

        if (loadedModel == null || !loadedModel.isActual()) {
            return loadedModel;
        }

        counterModel = loadedModel;
        counterView.updateUI(counterModel);
        return null;
    }

    private CounterModel loadModelFromFile(Context context) {
        CounterModel loadedModel = null;
        File saveFile = new File(context.getFilesDir(), "save.bin");
        try {
            FileInputStream fileInputStream = new FileInputStream(saveFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            loadedModel = (CounterModel) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            Toast.makeText(context.getApplicationContext(), "No backup found", Toast.LENGTH_SHORT).show();
        }

        return loadedModel;
    }

    public void saveToMemory(Context context) {
        File saveFile = new File(context.getFilesDir(), "save.bin");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(counterModel);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            Toast.makeText(context, "Unable to save backup", Toast.LENGTH_SHORT).show();
        }
    }
}

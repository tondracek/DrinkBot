package com.thomasthedeveloper.drinkbot.counter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

/**
 * This class handles communication between counterModel and counterView
 * and loads data saved in memory
 */
public class Counter {
    private final CounterView counterView;
    private CounterModel counterModel;

    @SuppressLint("ClickableViewAccessibility")
    public Counter(CounterView counterView, Context context) {
        this.counterView = counterView;
        counterModel = CounterModel.loadFromMemory(context);

        counterView.updateUI(counterModel);

        counterView.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                goalSetPopUp(view);
                counterView.updateUI(counterModel);
            }

            return true;
        });
    }

    public void updateUI() {
        counterView.updateUI(counterModel);
    }

    public void addToTotal(int addAmount) {
        counterModel.addToTotal(addAmount);
        updateUI();
    }

    public void removeLastAdd() {
        counterModel.removeLastAdd();
        updateUI();
    }

    public void deleteTotal() {
        addToTotal(-counterModel.getTotal());
    }

    public int getTotal() {
        return counterModel.getTotal();
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
            updateUI();
        });
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel());
        builder.show();
    }

    /**
     * @return outdated {@link CounterModel} or null if it is not outdated
     */
    public CounterModel updateOutdatedModel() {
        if (!counterModel.isActual()) {
            CounterModel old = counterModel;

            counterModel = new CounterModel();
            counterModel.setGoal(old.getGoal());

            updateUI();
            return old;
        }

        updateUI();
        return null;
    }

    public void saveToMemory(Context context) {
        counterModel.saveToMemory(context);
    }
}

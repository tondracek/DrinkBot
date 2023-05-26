package com.thomasthedeveloper.drinkbot;

import static android.view.View.OnClickListener;
import static android.view.View.OnTouchListener;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.text.InputType;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class TotalCounter {
    int total = 0;
    int goal = 2000;
    ArrayList<Integer> history = new ArrayList<>();

    TextView goalView;
    TextView totalView;
    ImageButton backButton;
    ImageButton deleteButton;
    ProgressBar progressBar;

    @SuppressLint("ClickableViewAccessibility")
    public TotalCounter(MainActivity activity) {
        this.goalView = activity.findViewById(R.id.goal_view);
        this.totalView = activity.findViewById(R.id.total_view);
        this.backButton = activity.findViewById(R.id.back_button);
        this.deleteButton = activity.findViewById(R.id.delete_button);
        this.progressBar = activity.findViewById(R.id.progressBar);

        setGoal(2000);
        setTotal(0);

        OnClickListener openDialogListener = openDialogSetup();
        goalView.setOnClickListener(openDialogListener);
        totalView.setOnClickListener(openDialogListener);

        backButton.setOnTouchListener(buttonAnimation());
        deleteButton.setOnTouchListener(buttonAnimation());

        backButton.setOnClickListener(view -> removeLastAdd());
        deleteButton.setOnClickListener(view -> addToTotal(-total));
    }

    OnClickListener openDialogSetup() {
        return view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Choose a new goal");

            EditText input = new EditText(view.getContext());
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setView(input);

            builder.setPositiveButton("Set", (dialogInterface, i) -> {
                int new_goal = Integer.parseInt(String.valueOf(input.getText()));
                TotalCounter.this.setGoal(new_goal);
            });
            builder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.cancel());
            builder.show();
        };
    }

    @SuppressLint("ClickableViewAccessibility")
    OnTouchListener buttonAnimation() {
        return (view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(50).start();
            }
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.animate().scaleX(1).scaleY(1).setDuration(50).start();
            }
            return false;
        };
    }

    void setGoal(int new_goal) {
        goal = new_goal;
        goalView.setText(String.format("goal: %s ml", goal));
        updateProgressBar();
    }

    int getGoal() {
        return goal;
    }

    void setTotal(int new_total) {
        total = new_total;
        totalView.setText(String.valueOf(new_total));
        updateProgressBar();
    }

    void addToTotal(int add_amount) {
        history.add(add_amount);
        setTotal(total + add_amount);
    }

    void removeLastAdd() {
        if (history.size() > 0) {
            int i = history.size() - 1;
            setTotal(total - history.get(i));
            history.remove(i);
        }
    }

    int getTotal() {
        return total;
    }

    void updateProgressBar() {
        int start_value = progressBar.getProgress();
        int end_value = 100 * total / goal;

        ObjectAnimator animator;
        animator = ObjectAnimator.ofInt(progressBar, "progress", start_value, end_value);
        animator.setDuration(100);
        animator.start();

        progressBar.setProgress(end_value);
    }
}
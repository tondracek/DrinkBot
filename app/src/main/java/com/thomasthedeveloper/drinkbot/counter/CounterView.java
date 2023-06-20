package com.thomasthedeveloper.drinkbot.counter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.thomasthedeveloper.drinkbot.MVPModel;
import com.thomasthedeveloper.drinkbot.MVPView;
import com.thomasthedeveloper.drinkbot.R;

public class CounterView extends ConstraintLayout implements MVPView {
    private TextView goalView;
    private TextView totalView;
    private ProgressBar progressBar;

    public void init(Context context) {
        inflate(context, R.layout.counter_layout, this);

        this.goalView = findViewById(R.id.goal_view);
        this.totalView = findViewById(R.id.total_view);
        this.progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void updateUI(MVPModel mvpModel) {
        CounterModel counterModel = (CounterModel) mvpModel;

        String goalText = "Goal: " + counterModel.getGoal() + " ml";
        goalView.setText(goalText);
        totalView.setText(String.valueOf(counterModel.getTotal()));

        updateProgressBar(counterModel);
    }

    public CounterView(Context context) {
        super(context);
        init(context);
    }

    public CounterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CounterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void updateProgressBar(CounterModel counterModel) {
        int start_value = progressBar.getProgress();

        int end_value = 0;
        if (counterModel.getGoal() != 0) {
            end_value = 100 * counterModel.getTotal() / counterModel.getGoal();
        }

        ObjectAnimator animator;
        animator = ObjectAnimator.ofInt(progressBar, "progress", start_value, end_value);
        animator.setDuration(100);
        animator.start();

        progressBar.setProgress(end_value);
    }
}

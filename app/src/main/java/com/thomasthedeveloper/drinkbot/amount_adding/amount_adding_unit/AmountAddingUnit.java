package com.thomasthedeveloper.drinkbot.amount_adding.amount_adding_unit;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.thomasthedeveloper.drinkbot.MVPPresenter;
import com.thomasthedeveloper.drinkbot.counter.Counter;

public class AmountAddingUnit implements MVPPresenter {
    AmountAddingUnitView unitView;
    AmountAddingUnitModel unitModel;

    @SuppressLint("ClickableViewAccessibility")
    public AmountAddingUnit(Context context, int amount, Counter counter) {
        unitView = new AmountAddingUnitView(context);
        unitModel = new AmountAddingUnitModel(amount);

        updateUI();

        unitView.setOnClickListener(view -> {
            counter.addToTotal(unitModel.getAmount());
        });


        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.9f, 1, 0.9f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(100);

        unitView.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case (MotionEvent.ACTION_DOWN):
                    scaleDown(unitView);
                    break;
                case (MotionEvent.ACTION_UP):
                case (MotionEvent.ACTION_CANCEL):
                    scaleUp(unitView);
                    break;
            }

            return false;
        });

    }


    private void scaleDown(ViewGroup view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 0.9f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.9f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.setDuration(200);
        animatorSet.start();
    }

    private void scaleUp(ViewGroup view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY);
        animatorSet.setDuration(200);
        animatorSet.start();
    }

    @Override
    public void updateUI() {
        unitView.updateUI(unitModel);
    }

    public ViewGroup getView() {
        return unitView;
    }
}

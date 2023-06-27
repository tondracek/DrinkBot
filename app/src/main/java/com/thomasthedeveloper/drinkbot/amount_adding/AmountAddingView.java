package com.thomasthedeveloper.drinkbot.amount_adding;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.thomasthedeveloper.drinkbot.MVPModel;
import com.thomasthedeveloper.drinkbot.MVPView;
import com.thomasthedeveloper.drinkbot.amount_adding.amount_adding_unit.AmountAddingUnit;

public class AmountAddingView extends LinearLayout implements MVPView {

    public AmountAddingView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public AmountAddingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AmountAddingView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public void init(Context context) {
        setOrientation(HORIZONTAL);
    }

    @Override
    public void updateUI(MVPModel mvpModel) {
        AmountAddingModel addingModel = (AmountAddingModel) mvpModel;

        removeAllViews();
        for (AmountAddingUnit addingUnit : addingModel.getAddingUnits()) {
            addView(addingUnit.getView());
        }
    }
}

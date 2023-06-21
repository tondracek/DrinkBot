package com.thomasthedeveloper.drinkbot.drinking_history;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.navigation.NavigationView;
import com.thomasthedeveloper.drinkbot.MVPModel;
import com.thomasthedeveloper.drinkbot.MVPView;
import com.thomasthedeveloper.drinkbot.R;
import com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit.DrinkingHistoryUnit;
import com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit.DrinkingHistoryUnitModel;
import com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit.DrinkingHistoryUnitView;

public class DrinkingHistoryView extends LinearLayout implements MVPView {
    public DrinkingHistoryView(Context context) {
        super(context);
        init(context);
    }

    public DrinkingHistoryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DrinkingHistoryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public void init(Context context) {
        inflate(context, R.layout.drinking_history_layout, this);
    }

    @Override
    public void updateUI(MVPModel mvpModel) {
        DrinkingHistoryModel historyModel = (DrinkingHistoryModel) mvpModel;

        for (DrinkingHistoryUnitModel unitModel : historyModel.getHistory()) {
            DrinkingHistoryUnitView unitView = new DrinkingHistoryUnitView(getContext());
            DrinkingHistoryUnit unit = new DrinkingHistoryUnit(unitModel, unitView);

            addView(unit.getView());
        }
    }
}

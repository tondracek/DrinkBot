package com.thomasthedeveloper.drinkbot.amount_adding.amount_adding_unit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.thomasthedeveloper.drinkbot.MVPModel;
import com.thomasthedeveloper.drinkbot.MVPView;
import com.thomasthedeveloper.drinkbot.R;

public class AmountAddingUnitView extends LinearLayout implements MVPView {
    TextView amountView;

    public AmountAddingUnitView(Context context) {
        super(context);
        init(context);
    }

    public AmountAddingUnitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AmountAddingUnitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public void init(Context context) {
        inflate(context, R.layout.amount_adding_unit_layout, this);

        amountView = findViewById(R.id.amountAddingUnitView);
    }

    @Override
    public void updateUI(MVPModel mvpModel) {
        AmountAddingUnitModel unitModel = (AmountAddingUnitModel) mvpModel;

        amountView.setText(unitModel.toString());
    }
}

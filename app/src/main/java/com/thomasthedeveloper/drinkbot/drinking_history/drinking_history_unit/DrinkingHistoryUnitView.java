package com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.thomasthedeveloper.drinkbot.MVPModel;
import com.thomasthedeveloper.drinkbot.MVPView;
import com.thomasthedeveloper.drinkbot.R;

public class DrinkingHistoryUnitView extends ConstraintLayout implements MVPView {
    private TextView amount;
    private TextView date;
    private ImageView checked;

    public DrinkingHistoryUnitView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public DrinkingHistoryUnitView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DrinkingHistoryUnitView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public void init(Context context) {
        inflate(context, R.layout.drinking_history_unit_layout, this);

        date = findViewById(R.id.dateTextView);
        amount = findViewById(R.id.amountTextView);
        checked = findViewById(R.id.isCheckedImage);
    }

    @Override
    public void updateUI(MVPModel mvpModel) {
        DrinkingHistoryUnitModel unitModel = (DrinkingHistoryUnitModel) mvpModel;

        date.setText(unitModel.getDateString());
        amount.setText(unitModel.getAmount());

        if (unitModel.isAccomplished()) {
            checked.setImageResource(R.drawable.outline_check_circle_24);
        } else {
            checked.setImageResource(R.drawable.outline_cancel_24);
        }
    }
}

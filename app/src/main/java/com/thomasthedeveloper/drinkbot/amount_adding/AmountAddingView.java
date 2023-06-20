package com.thomasthedeveloper.drinkbot.amount_adding;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.thomasthedeveloper.drinkbot.R;
import com.thomasthedeveloper.drinkbot.MVPModel;
import com.thomasthedeveloper.drinkbot.MVPView;

public class AmountAddingView extends ConstraintLayout implements MVPView {
    private EditText amountEdit;
    private Button addButton;

    @Override
    public void init(Context context) {
        inflate(context, R.layout.adding_amount_layout, this);

        this.amountEdit = findViewById(R.id.amountEdit);
        this.addButton = findViewById(R.id.addButton);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                amountEdit.setText(String.valueOf(50 * i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                amountEdit.setText(String.valueOf(50 * seekBar.getProgress()));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public AmountAddingView(Context context) {
        super(context);
        init(context);
    }

    public AmountAddingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AmountAddingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public void updateUI(MVPModel mvpModel) {
        // Nothing to update
    }

    public int getAmount() {
        try {
            return Integer.parseInt(String.valueOf(amountEdit.getText()));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public Button getAddButton() {
        return addButton;
    }

    public void killFocus(InputMethodManager manager) {
        manager.hideSoftInputFromWindow(amountEdit.getWindowToken(), 0);
        amountEdit.clearFocus();
    }
}

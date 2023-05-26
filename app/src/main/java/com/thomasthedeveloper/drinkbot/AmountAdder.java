package com.thomasthedeveloper.drinkbot;

import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class AmountAdder {
    int amount = 0;
    SeekBar seekBar;
    EditText amountEdit;
    Button addButton;
    TotalCounter totalCounter;

    public AmountAdder(MainActivity activity, TotalCounter totalCounter) {
        this.seekBar = activity.findViewById(R.id.seekBar);
        this.amountEdit = activity.findViewById(R.id.amountEdit);
        this.addButton = activity.findViewById(R.id.addButton);
        this.totalCounter = totalCounter;

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setAmount(50 * i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                setAmount(50 * seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        addButton.setOnClickListener(view -> {
            int amount = 0;
            if (!String.valueOf(amountEdit.getText()).equals("")) {
                amount = Integer.parseInt(String.valueOf(amountEdit.getText()));
            }
            totalCounter.addToTotal(amount);
        });
    }

    public void setAmount(int amount) {
        this.amount = amount;
        amountEdit.setText(String.valueOf(amount));
    }
}

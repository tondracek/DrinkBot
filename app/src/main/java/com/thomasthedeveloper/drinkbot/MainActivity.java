package com.thomasthedeveloper.drinkbot;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.thomasthedeveloper.drinkbot.amount_adding.AmountAdding;
import com.thomasthedeveloper.drinkbot.counter.Counter;
import com.thomasthedeveloper.drinkbot.counter.CounterModel;
import com.thomasthedeveloper.drinkbot.drinking_history.DrinkingHistoryModel;
import com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit.DrinkingHistoryUnitModel;

public class MainActivity extends AppCompatActivity {
    private Counter counter;
    private DrinkingHistoryModel drinkingHistoryModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = new Counter(findViewById(R.id.counterView));
        drinkingHistoryModel = DrinkingHistoryModel.loadFromMemory(getApplicationContext());
        AmountAdding amountAdding = new AmountAdding(findViewById(R.id.amountAddingView), counter, this);

        CounterModel outdatedModel = counter.loadFromMemory(getApplicationContext());
        drinkingHistoryModel.add(DrinkingHistoryUnitModel.convert(outdatedModel));

        TextView tipTextView = findViewById(R.id.tipTextView);
        tipTextView.setVisibility(View.VISIBLE);
        tipTextView.setSelected(true);

        
    }

    @Override
    protected void onPause() {
        super.onPause();

        counter.saveToMemory(getApplicationContext());
        drinkingHistoryModel.saveToMemory(getApplicationContext());
    }
}
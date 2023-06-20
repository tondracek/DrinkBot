package com.thomasthedeveloper.drinkbot;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.thomasthedeveloper.drinkbot.amount_adding.AmountAdding;
import com.thomasthedeveloper.drinkbot.counter.Counter;
import com.thomasthedeveloper.drinkbot.counter.CounterModel;
import com.thomasthedeveloper.drinkbot.drinking_history.DrinkingHistory;
import com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit.DrinkingHistoryUnitModel;

public class MainActivity extends AppCompatActivity {
    private Counter counter;
    private DrinkingHistory drinkingHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = new Counter(findViewById(R.id.counterView));
        drinkingHistory = new DrinkingHistory(findViewById(R.id.drinkingHistoryView));

        AmountAdding amountAdding = new AmountAdding(findViewById(R.id.amountAddingView), counter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // CLOSING KEYBOARD AFTER FOCUS LOST
        View main = findViewById(R.id.mainLayout);
        main.setOnClickListener(view -> {
            InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            amountAdding.killFocus(manager);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        CounterModel outdatedModel = counter.loadFromMemory(getApplicationContext());
        drinkingHistory.loadFromMemory(getApplicationContext());

        if (outdatedModel != null) {
            drinkingHistory.add(DrinkingHistoryUnitModel.convert(outdatedModel));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        counter.saveToMemory(getApplicationContext());

        drinkingHistory.saveToMemory(getApplicationContext());
    }
}
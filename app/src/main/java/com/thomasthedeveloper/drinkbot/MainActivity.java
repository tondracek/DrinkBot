package com.thomasthedeveloper.drinkbot;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        Context context = this;

        drinkingHistoryModel = DrinkingHistoryModel.loadFromMemory(context);
        counter = new Counter(findViewById(R.id.counterView), context);
        new AmountAdding(findViewById(R.id.amountAddingView), counter, this);

        CounterModel outdatedModel = counter.updateOutdatedModel();

        if (outdatedModel != null) {
            new Thread(() -> {
                drinkingHistoryModel.add(DrinkingHistoryUnitModel.convert(outdatedModel), context);
            }).start();
        }

        TextView tipTextView = findViewById(R.id.tipTextView);
        tipTextView.setVisibility(View.VISIBLE);
        tipTextView.setSelected(true);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> counter.removeLastAdd());

        ImageButton deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(view -> counter.deleteTotal());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.historyButton) {
            Intent historyIntent = new Intent(this, DrinkingHistoryActivity.class);
            historyIntent.putExtra("DrinkingHistoryModel", drinkingHistoryModel);
            startActivity(historyIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();

        counter.saveToMemory(this);
    }
}
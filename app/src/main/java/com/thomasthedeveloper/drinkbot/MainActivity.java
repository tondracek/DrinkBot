package com.thomasthedeveloper.drinkbot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
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

        CounterModel outdatedModel = counter.updateOutdatedModel();

        if (outdatedModel != null) {
            new Thread(() -> {
                drinkingHistoryModel.add(DrinkingHistoryUnitModel.convert(outdatedModel), context);
            }).start();
        }

        new AmountAdding(findViewById(R.id.amountAddingView), counter, this);

        TextView tipTextView = findViewById(R.id.tipTextView);
        tipTextView.setSelected(true);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(view -> counter.removeLastAdd());

        ImageButton deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(view -> counter.deleteTotal());


        /*

            TODO: Udělat animaci na add amount tlačítka

         */
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
    protected void onStart() {
        super.onStart();

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        int scroll = preferences.getInt("scrollTo", 0);

        HorizontalScrollView scrollView = findViewById(R.id.scrollAddingView);
        scrollView.post(() -> scrollView.setScrollX(scroll));
    }

    @Override
    protected void onPause() {
        super.onPause();

        counter.saveToMemory(this);

        HorizontalScrollView scrollView = findViewById(R.id.scrollAddingView);
        int scroll = scrollView.getScrollX();

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        preferences.edit().putInt("scrollTo", scroll).apply();
    }
}
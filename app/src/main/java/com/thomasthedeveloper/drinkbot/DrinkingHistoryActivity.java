package com.thomasthedeveloper.drinkbot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.thomasthedeveloper.drinkbot.drinking_history.DrinkingHistory;

public class DrinkingHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinking_history);

        new DrinkingHistory(findViewById(R.id.drinkingHistoryView), getApplicationContext());
    }
}
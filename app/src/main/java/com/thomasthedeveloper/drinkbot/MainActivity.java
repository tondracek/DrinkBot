package com.thomasthedeveloper.drinkbot;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TotalCounter totalCounter;
    int total, goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalCounter = new TotalCounter(this);
        AmountAdder amountAdder = new AmountAdder(this, totalCounter);

        // CLOSING KEYBOARD AFTER FOCUS LOST
        View main = findViewById(R.id.mainLayout);
        main.setOnClickListener(view -> {
            InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            kill_focus(amountAdder.amountEdit, manager);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = getSharedPreferences("DrinkBot", Context.MODE_PRIVATE);
        if (compareStrings(getDate(), preferences.getString("time", ""))) {
            total = 0;
        } else {
            total = preferences.getInt("total", 0);
        }
        goal = preferences.getInt("goal", 2000);

        totalCounter.setTotal(total);
        totalCounter.setGoal(goal);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences preferences = getSharedPreferences("DrinkBot", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("goal", totalCounter.getGoal());
        editor.putInt("total", totalCounter.getTotal());
        editor.putString("time", getDate());
        editor.apply();
    }

    void kill_focus(View focused_view, InputMethodManager manager) {
        manager.hideSoftInputFromWindow(focused_view.getWindowToken(), 0);
        focused_view.clearFocus();
    }

    String getDate() {
        Date date = new Date();

        return (String) DateFormat.format("yyyy-MM-dd", date);
    }

    Boolean compareStrings(String str1, String str2) {
        /**
         * Returns true if the first string is bigger.
         * Returns false if the second is bigger or they are equal.
         **/

        int size = Math.min(str1.length(), str2.length());

        for (int i = 0; i < size; i++) {
            int char1 = str1.charAt(i);
            int char2 = str2.charAt(i);

            if (char1 > char2) {
                return true;
            }
            if (char1 < char2) {
                return false;
            }
        }

        return str1.length() > str2.length();
    }
}
package com.thomasthedeveloper.drinkbot.amount_adding.amount_adding_unit;

import androidx.annotation.NonNull;

import com.thomasthedeveloper.drinkbot.MVPModel;

public class AmountAddingUnitModel implements MVPModel {
    private final int amount;

    public AmountAddingUnitModel(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @NonNull
    @Override
    public String toString() {
        return amount + " ml";
    }
}

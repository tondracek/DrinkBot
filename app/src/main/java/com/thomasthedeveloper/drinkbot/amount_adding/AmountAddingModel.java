package com.thomasthedeveloper.drinkbot.amount_adding;

import android.content.Context;

import com.thomasthedeveloper.drinkbot.MVPModel;
import com.thomasthedeveloper.drinkbot.amount_adding.amount_adding_unit.AmountAddingUnit;
import com.thomasthedeveloper.drinkbot.counter.Counter;

import java.util.ArrayList;
import java.util.List;

public class AmountAddingModel implements MVPModel {
    private final List<AmountAddingUnit> addingUnits = new ArrayList<>(10);

    public AmountAddingModel(Context context, int[] amounts, Counter counter) {
        for (int amount : amounts) {
            addingUnits.add(new AmountAddingUnit(context, amount, counter));
        }
    }

    public List<AmountAddingUnit> getAddingUnits() {
        return addingUnits;
    }
}

package com.thomasthedeveloper.drinkbot.drinking_history;

import com.thomasthedeveloper.drinkbot.MVPModel;
import com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit.DrinkingHistoryUnit;
import com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit.DrinkingHistoryUnitModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class DrinkingHistoryModel implements MVPModel, Serializable {
    private final SortedSet<DrinkingHistoryUnitModel> history = new TreeSet<>(Comparator.reverseOrder());

    public void add(DrinkingHistoryUnitModel unit) {
        history.add(unit);
    }

    public Collection<DrinkingHistoryUnitModel> getHistory() {
        return history;
    }
}

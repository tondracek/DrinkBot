package com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit;

import com.thomasthedeveloper.drinkbot.MVPModel;
import com.thomasthedeveloper.drinkbot.counter.CounterModel;

import java.time.LocalDate;

public class DrinkingHistoryUnitModel implements MVPModel, Comparable<DrinkingHistoryUnitModel> {
    private final int total;
    private final int goal;
    private final LocalDate date;

    public DrinkingHistoryUnitModel(int total, int goal, LocalDate date) {
        this.total = total;
        this.goal = goal;
        this.date = date;
    }

    public String getDateString() {
        return String.valueOf(date);
    }

    public String getAmount() {
        return total + "/" + goal + " ml";
    }

    public boolean isAccomplished() {
        return total >= goal;
    }

    @Override
    public int compareTo(DrinkingHistoryUnitModel other) {
        return date.compareTo(other.date);
    }

    public static DrinkingHistoryUnitModel convert(CounterModel counterModel) {
        if (counterModel == null)
            return null;
        return new DrinkingHistoryUnitModel(counterModel.getTotal(), counterModel.getGoal(), counterModel.getDate());
    }
}

package com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit;

import com.thomasthedeveloper.drinkbot.MVPModel;
import com.thomasthedeveloper.drinkbot.counter.CounterModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

public class DrinkingHistoryUnitModel implements Serializable, MVPModel, Comparable<DrinkingHistoryUnitModel> {
    private final int total;
    private final int goal;
    private final LocalDate date;

    public DrinkingHistoryUnitModel(int total, int goal, LocalDate date) {
        this.total = total;
        this.goal = goal;
        this.date = date;
    }

    public static DrinkingHistoryUnitModel convert(CounterModel counterModel) {
        if (counterModel == null) return null;
        return new DrinkingHistoryUnitModel(counterModel.getTotal(), counterModel.getGoal(), counterModel.getDate());
    }

    public String getDateString() {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DrinkingHistoryUnitModel unitModel = (DrinkingHistoryUnitModel) o;
        return date.equals(unitModel.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}

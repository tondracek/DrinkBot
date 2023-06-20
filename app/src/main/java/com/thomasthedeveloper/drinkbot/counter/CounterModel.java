package com.thomasthedeveloper.drinkbot.counter;

import com.thomasthedeveloper.drinkbot.MVPModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class CounterModel implements Serializable, MVPModel {
    private int total;
    private int goal;
    private final ArrayList<DrinkRecord> history = new ArrayList<>();
    private final LocalDate date;

    public CounterModel() {
        total = 0;
        goal = 2000;
        date = LocalDate.now();
    }

    public void addToTotal(int add_amount) {
        history.add(new DrinkRecord(total, goal));
        setTotal(total + add_amount);
    }

    public void removeLastAdd() {
        DrinkRecord last = history.remove(history.size() - 1);

        if (last != null) {
            setTotal(last.getTotal());
            setGoal(last.getGoal());
        }
    }

    public boolean isActual() {
        LocalDate actualDate = LocalDate.now();

        return date.compareTo(actualDate) >= 0;
    }

    public void setGoal(int new_goal) {
        goal = new_goal;
    }

    public int getGoal() {
        return goal;
    }

    public void setTotal(int new_total) {
        total = new_total;
    }

    public int getTotal() {
        return total;
    }

    public LocalDate getDate() {
        return date;
    }

    private static class DrinkRecord implements Serializable {
        private final int total;
        private final int goal;

        public DrinkRecord(int total, int goal) {
            this.total = total;
            this.goal = goal;
        }

        public int getTotal() {
            return total;
        }

        public int getGoal() {
            return goal;
        }
    }
}
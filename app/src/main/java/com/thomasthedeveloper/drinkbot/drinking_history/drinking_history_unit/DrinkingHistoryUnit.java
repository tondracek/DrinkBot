package com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit;

import java.io.Serializable;

public class DrinkingHistoryUnit implements Serializable, Comparable<DrinkingHistoryUnit> {
    private final DrinkingHistoryUnitModel unitModel;
    private final DrinkingHistoryUnitView unitView;

    public DrinkingHistoryUnit(DrinkingHistoryUnitModel unitModel, DrinkingHistoryUnitView unitView) {
        this.unitModel = unitModel;
        this.unitView = unitView;
    }

    public DrinkingHistoryUnitView getView() {
        unitView.updateUI(unitModel);
        return unitView;
    }

    @Override
    public int compareTo(DrinkingHistoryUnit other) {
        return unitModel.compareTo(other.unitModel);
    }
}

package com.thomasthedeveloper.drinkbot.drinking_history.drinking_history_unit;

import com.thomasthedeveloper.drinkbot.MVPPresenter;

import java.io.Serializable;

public class DrinkingHistoryUnit implements MVPPresenter, Serializable, Comparable<DrinkingHistoryUnit> {
    private final DrinkingHistoryUnitModel unitModel;
    private final DrinkingHistoryUnitView unitView;

    public DrinkingHistoryUnit(DrinkingHistoryUnitModel unitModel, DrinkingHistoryUnitView unitView) {
        this.unitModel = unitModel;
        this.unitView = unitView;

        updateUI();
    }

    public DrinkingHistoryUnitView getView() {
        return unitView;
    }

    @Override
    public void updateUI() {
        unitView.updateUI(unitModel);
    }

    @Override
    public int compareTo(DrinkingHistoryUnit other) {
        return unitModel.compareTo(other.unitModel);
    }
}

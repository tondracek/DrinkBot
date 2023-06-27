package com.thomasthedeveloper.drinkbot;

import android.view.ViewGroup;

/**
 * Classes implementing {@link MVPPresenter} connect functionality from
 * {@link MVPModel} with view from {@link MVPView} through saving both
 * in private variables.
 */
public interface MVPPresenter {
    /**
     * Updates View of {@link MVPView} by passing {@link MVPModel} as argument.
     */
    void updateUI();
    ViewGroup getView();
}

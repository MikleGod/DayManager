package com.example.mikle.daymanager.presenter.impl;

import com.example.mikle.daymanager.presenter.MainActivityPresenter;
import com.example.mikle.daymanager.view.MainActivity;

public abstract class BaseMainActivityPresenter implements MainActivityPresenter{

    protected MainActivity activity;

    @Override
    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }
}

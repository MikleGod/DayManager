package com.example.mikle.daymanager.presenter.impl;

import android.view.View;

import com.example.mikle.daymanager.presenter.AuthActivityPresenter;
import com.example.mikle.daymanager.view.AuthActivity;

public class RegistrationPresenter implements AuthActivityPresenter {
    @Override
    public View.OnClickListener getLoginBListener() {
        return null;
    }

    @Override
    public View.OnClickListener getRegistrationBListener() {
        return null;
    }

    @Override
    public void setActivity(AuthActivity activity) {

    }
}

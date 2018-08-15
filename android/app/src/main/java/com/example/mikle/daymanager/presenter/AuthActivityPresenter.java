package com.example.mikle.daymanager.presenter;

import android.view.View;

import com.example.mikle.daymanager.view.AuthActivity;

public interface AuthActivityPresenter extends Presenter {
    View.OnClickListener getLoginBListener();
    View.OnClickListener getRegistrationBListener();
    void setActivity(AuthActivity activity);
}

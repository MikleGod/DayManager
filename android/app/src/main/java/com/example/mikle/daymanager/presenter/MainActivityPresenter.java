package com.example.mikle.daymanager.presenter;

import android.app.Dialog;

import com.example.mikle.daymanager.view.AuthActivity;
import com.example.mikle.daymanager.view.MainActivity;

public interface MainActivityPresenter extends Presenter {
    int DATE_DIALOG_ID = 1;

    void setActivity(MainActivity activity);

    void onInit();

    Dialog getDateDialog();
}

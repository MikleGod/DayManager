package com.example.mikle.daymanager.presenter;

import com.example.mikle.daymanager.view.AuthActivity;
import com.example.mikle.daymanager.view.MainActivity;

public interface MainActivityPresenter extends Presenter {

    void setActivity(MainActivity activity);

    void onInit();
}

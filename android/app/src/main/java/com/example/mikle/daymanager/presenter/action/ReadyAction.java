package com.example.mikle.daymanager.presenter.action;

import org.json.JSONObject;

public interface ReadyAction {
    void onError();
    void onSuccess(JSONObject answer);
}

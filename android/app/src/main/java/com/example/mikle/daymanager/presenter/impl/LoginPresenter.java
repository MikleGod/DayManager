package com.example.mikle.daymanager.presenter.impl;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mikle.daymanager.DayManagerApplication;
import com.example.mikle.daymanager.R;
import com.example.mikle.daymanager.entity.CashFlowItem;
import com.example.mikle.daymanager.entity.Role;
import com.example.mikle.daymanager.entity.Session;
import com.example.mikle.daymanager.entity.TimeManagerItem;
import com.example.mikle.daymanager.entity.User;
import com.example.mikle.daymanager.entity.dto.LoginDto;
import com.example.mikle.daymanager.internet.LoginTask;
import com.example.mikle.daymanager.presenter.AuthActivityPresenter;
import com.example.mikle.daymanager.presenter.action.ReadyAction;
import com.example.mikle.daymanager.view.AuthActivity;
import com.example.mikle.daymanager.view.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginPresenter implements AuthActivityPresenter {

    private AuthActivity activity;

    public void setActivity(AuthActivity activity) {
        this.activity = activity;
    }

    @Override
    public View.OnClickListener getLoginBListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDto data = activity.getETData();
                String vMessage;
                if ((vMessage = validateData(data)) == null) {
                    changeButtonEnabled();
                    new LoginTask(new LoginAction()).execute(data);
                } else {
                    Toast.makeText(activity, vMessage, Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void changeButtonEnabled() {
        View loginB = activity.findViewById(R.id.loginB);
        loginB.setEnabled(!loginB.isEnabled());
    }

    private String validateData(LoginDto data) {
        return null;
    }

    @Override
    public View.OnClickListener getRegistrationBListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Sorry, not yet", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private class LoginAction implements ReadyAction {

        @Override
        public void onError() {
            changeButtonEnabled();
            Toast.makeText(activity, "Smth wrong, sorry", Toast.LENGTH_SHORT).show();
        }

        private void onError(JSONObject answer) {
            changeButtonEnabled();
            try {
                Toast.makeText(activity, answer.getString("message"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                onError();
            }
        }

        @Override
        public void onSuccess(JSONObject answer) {
            try {
                Session session = ((DayManagerApplication) activity.getApplication()).getSession();
                session.setUser(new User(
                        Role.valueOf(answer.getString("role").toUpperCase()),
                        answer.getString("usr_nickname"),
                        answer.getInt("usr_id"))
                );
                session.setCashFlowItems(parseCashFlowItems(answer));
                session.setTimeManagerItems(parseTimeManagerItems(answer));
                activity.startActivity(new Intent(activity, MainActivity.class));
            } catch (JSONException e) {
                Log.e("Login Presenter: ", answer.toString(), e);
                onError(answer);
            }
        }

        private List<TimeManagerItem> parseTimeManagerItems(JSONObject answer) throws JSONException {
            List<TimeManagerItem> items = new ArrayList<>();
            JSONArray tmItems = answer.getJSONArray("tmItems");
            for (int i=0; i < tmItems.length(); i++){
                JSONObject cfi = tmItems.getJSONObject(i);
                items.add(new TimeManagerItem(cfi.getString("name"), cfi.getInt("id")));
            }
            return items;
        }

        private List<CashFlowItem> parseCashFlowItems(JSONObject answer) throws JSONException {
            List<CashFlowItem> items = new ArrayList<>();
            JSONArray cfItems = answer.getJSONArray("cfItems");
            for (int i=0; i < cfItems.length(); i++){
                JSONObject cfi = cfItems.getJSONObject(i);
                items.add(new CashFlowItem(cfi.getString("name"), cfi.getInt("id")));
            }
            return items;
        }
    }
}

package com.example.mikle.daymanager.presenter.impl;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mikle.daymanager.DayManagerApplication;
import com.example.mikle.daymanager.R;
import com.example.mikle.daymanager.entity.Session;
import com.example.mikle.daymanager.entity.dto.CashFlowStatDto;
import com.example.mikle.daymanager.entity.dto.StatItem;
import com.example.mikle.daymanager.entity.dto.TimeManagerStatDto;
import com.example.mikle.daymanager.internet.GetStatTask;
import com.example.mikle.daymanager.presenter.action.ReadyAction;
import com.example.mikle.daymanager.presenter.adapter.StatLVAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StatisticsPresenter extends BaseMainActivityPresenter {
    private ListView statLV;
    private List<StatItem> statItems;
    private StatLVAdapter adapter;

    @Override
    public void onInit() {
        initLV();
        updateLV();
    }

    private void initLV() {
        statLV = activity.findViewById(R.id.statLV);
        statItems = new ArrayList<>();
        adapter = new StatLVAdapter(activity, statItems);
        statLV.setAdapter(adapter);
    }

    private void updateLV() {
        Session session = ((DayManagerApplication) activity.getApplication()).getSession();
        new GetStatTask(new GetStatReadyAction(), session.getContext(), session.getHost()).execute("");
    }

    @Override
    protected void onDateSet(int year, int month, int day) {

    }

    private class GetStatReadyAction implements ReadyAction{
        @Override
        public void onError() {
            Toast.makeText(activity, "Smth wrong!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(JSONObject answer) {
            try {
                List<StatItem> statItems = new ArrayList<>();

                JSONArray cashArray = answer.getJSONArray("cashFlowStatDtos");
                JSONArray timeArray = answer.getJSONArray("timeManagerStatDtos");

                for (int i = 0; i<cashArray.length();i++){
                    JSONObject object = cashArray.getJSONObject(i);
                    statItems.add(new StatItem(object.getJSONObject("cfi").getString("name"), object.getString("cost")));
                }

                for (int i = 0; i<timeArray.length();i++){
                    JSONObject object = timeArray.getJSONObject(i);
                    statItems.add(new StatItem(object.getJSONObject("tmi").getString("name"), object.getString("time")));
                }

                StatisticsPresenter.this.statItems.clear();
                StatisticsPresenter.this.statItems.addAll(statItems);

                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                onError(answer);
            }
        }

        private void onError(JSONObject answer) {
            try {
                Toast.makeText(activity, answer.getString("message"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                onError();
            }
        }
    }
}

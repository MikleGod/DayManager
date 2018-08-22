package com.example.mikle.daymanager.presenter.impl;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mikle.daymanager.DayManagerApplication;
import com.example.mikle.daymanager.R;
import com.example.mikle.daymanager.entity.CashFlowItem;
import com.example.mikle.daymanager.entity.CashFlowPlanItem;
import com.example.mikle.daymanager.entity.Role;
import com.example.mikle.daymanager.entity.Session;
import com.example.mikle.daymanager.entity.User;
import com.example.mikle.daymanager.internet.AddCfiTask;
import com.example.mikle.daymanager.internet.AddCfpiTask;
import com.example.mikle.daymanager.internet.AddTmiTask;
import com.example.mikle.daymanager.internet.GetCfpiTask;
import com.example.mikle.daymanager.presenter.action.ReadyAction;
import com.example.mikle.daymanager.presenter.adapter.CfiSpinnerAdapter;
import com.example.mikle.daymanager.presenter.adapter.CfpiListViewAdapter;
import com.example.mikle.daymanager.util.DateParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CashFlowPresenter extends BaseMainActivityPresenter{
    private ListView listView;

    private Button addTmiButton;

    private Spinner cfiSpinner;
    private EditText costET;
    private Button addTmpiButton;

    private CfiSpinnerAdapter spinnerAdapter;
    private CfpiListViewAdapter listViewAdapter;

    private int selectedCfi;

    private List<CashFlowPlanItem> cfpItems;
    private String date;

    @Override
    public void onInit() {
        initViews();
        initSpinner();
        initListView();
        addTmiButton.setOnClickListener(getAddCfiListener());
        addTmpiButton.setOnClickListener(getCfpiListener());
    }

    @SuppressLint("SimpleDateFormat")
    private void initListView() {
        cfpItems = new ArrayList<>();
        listViewAdapter = new CfpiListViewAdapter(activity, R.layout.item_cfpi_list, cfpItems);
        listView.setAdapter(listViewAdapter);
        updateCfpiList(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
    }

    private void updateCfpiList(String date) {
        dateTV.setText(date);
        this.date = date;
        Session session = ((DayManagerApplication) activity.getApplication()).getSession();
        new GetCfpiTask(
                new GetCfpiReadyAction(),
                session.getContext(),
                session.getHost()
        ).execute(date);
    }

    private void initSpinner() {
        DayManagerApplication application = (DayManagerApplication) activity.getApplication();
        spinnerAdapter = new CfiSpinnerAdapter(activity, android.R.layout.simple_spinner_item, application.getSession().getCashFlowItems());
        cfiSpinner.setAdapter(spinnerAdapter);
        cfiSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCfi = ((CashFlowItem) adapterView.getSelectedItem()).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedCfi = ((CashFlowItem) adapterView.getItemAtPosition(0)).getId();
            }
        });
    }

    private View.OnClickListener getCfpiListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session session = ((DayManagerApplication) activity.getApplication()).getSession();
                String cost = costET.getText() + "";
                if (!cost.isEmpty()) {
                    new AddCfpiTask(
                            new AddCfpiReadyAction(),
                            session.getContext(),
                            session.getHost()
                    ).execute(new CashFlowPlanItem(0,
                            new CashFlowItem("", selectedCfi),
                            Double.parseDouble(cost), DateParser.parseDate(date),
                            null)
                    );
                } else {
                    Toast.makeText(activity, "Validation error!", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private View.OnClickListener getAddCfiListener() {
        return new View.OnClickListener() {
            private View eTView;

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                eTView = activity.getLayoutInflater().inflate(R.layout.layout_additem, null);
                builder.setTitle("Time item")
                        .setView(eTView)
                        .setPositiveButton("add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText editText = eTView.findViewById(R.id.addItemEditText);
                                Editable text = editText.getText();
                                if (!text.toString().isEmpty()) {
                                    addTmiButton.setEnabled(false);
                                    DayManagerApplication application = (DayManagerApplication) activity.getApplication();
                                    new AddCfiTask(
                                            new AddCfiReadyAction(),
                                            application.getSession().getContext(),
                                            application.getSession().getHost()
                                    ).execute("" + text);
                                } else {
                                    Toast.makeText(activity, "U must write smth!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).create().show();
            }
        };
    }

    private void initViews() {
        dateTV = activity.findViewById(R.id.date);
        dateTV.setOnClickListener(getDateTVListener());

        listView = activity.findViewById(R.id.cashListView);

        addTmiButton = activity.findViewById(R.id.addCfiButton);

        cfiSpinner = activity.findViewById(R.id.cfiSpinner);
        costET = activity.findViewById(R.id.costTextView);
        addTmpiButton = activity.findViewById(R.id.addCfpiButton);
    }

    @Override
    protected void onDateSet(int year, int month, int day) {
        updateCfpiList(year + "-" + (month+1) + "-" + day);
    }

    private class AddCfiReadyAction implements ReadyAction {

        @Override
        public void onError() {
            addTmiButton.setEnabled(true);
            Toast.makeText(activity, "Sorry, smth wrong", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(JSONObject answer) {
            addTmiButton.setEnabled(true);
            DayManagerApplication application = (DayManagerApplication) activity.getApplication();
            try {
                application.getSession().getCashFlowItems().add(new CashFlowItem(answer.getString("name"), answer.getInt("id")));
                spinnerAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                onError(answer);
            }
        }

        private void onError(JSONObject answer) {
            try {
                Toast.makeText(activity, answer.getString("message"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e1) {
                onError();
            }
        }
    }


    private class GetCfpiReadyAction implements ReadyAction {

        @Override
        public void onError() {
            Toast.makeText(activity, "Sorry, smth wrong", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(JSONObject answer) {
            try {
                JSONArray array = answer.getJSONArray("cfpItems");
                List<CashFlowPlanItem> cfpItems = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonCfpi = array.getJSONObject(i);
                    JSONObject jsonCfi = jsonCfpi.getJSONObject("cashFlowItem");
                    CashFlowItem cfi = new CashFlowItem(jsonCfi.getString("name"), jsonCfi.getInt("id"));
                    CashFlowPlanItem cfpi = new CashFlowPlanItem(
                            jsonCfpi.getInt("id"),
                            cfi,
                            jsonCfpi.getDouble("cost"),

                            new java.sql.Date(Long.parseLong(
                                    jsonCfpi.getString("date"))),
                            null
                    );
                    cfpItems.add(cfpi);
                }
                CashFlowPresenter.this.cfpItems.clear();
                CashFlowPresenter.this.cfpItems.addAll(cfpItems);
                listViewAdapter.notifyDataSetChanged();
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

    private class AddCfpiReadyAction implements ReadyAction {
        @Override
        public void onError() {
            Toast.makeText(activity, "Sorry, smth wrong", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(JSONObject answer) {
            try {
                JSONObject jsonCfpi = answer.getJSONObject("cfpi");
                JSONObject jsonCfi = jsonCfpi.getJSONObject("cashFlowItem");
                JSONObject jsonUser = jsonCfpi.getJSONObject("user");
                CashFlowPlanItem tmpi = new CashFlowPlanItem(
                        jsonCfpi.getInt("id"),
                        new CashFlowItem(jsonCfi.getString("name"), jsonCfi.getInt("id")),
                        jsonCfpi.getDouble("cost"),
                        new java.sql.Date(Long.parseLong(jsonCfpi.getString("date"))),
                        new User(Role.USER, jsonUser.getString("nickname"), jsonUser.getInt("id"))
                );
                cfpItems.add(tmpi);
                listViewAdapter.notifyDataSetChanged();
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

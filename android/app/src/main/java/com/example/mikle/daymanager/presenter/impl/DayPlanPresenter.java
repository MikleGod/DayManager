package com.example.mikle.daymanager.presenter.impl;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikle.daymanager.DayManagerApplication;
import com.example.mikle.daymanager.R;
import com.example.mikle.daymanager.entity.Role;
import com.example.mikle.daymanager.entity.Session;
import com.example.mikle.daymanager.entity.TimeManagerItem;
import com.example.mikle.daymanager.entity.TimeManagerPlanItem;
import com.example.mikle.daymanager.entity.User;
import com.example.mikle.daymanager.internet.AddTmiTask;
import com.example.mikle.daymanager.internet.AddTmpiTask;
import com.example.mikle.daymanager.internet.GetTmpiTask;
import com.example.mikle.daymanager.presenter.action.ReadyAction;
import com.example.mikle.daymanager.presenter.adapter.TmiSpinnerAdapter;
import com.example.mikle.daymanager.presenter.adapter.TmpiListViewAdapter;
import com.example.mikle.daymanager.presenter.validator.TimeValidator;
import com.example.mikle.daymanager.util.DateParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DayPlanPresenter extends BaseMainActivityPresenter {
    private ListView listView;

    private Button addTmiButton;

    private Spinner tmiSpinner;
    private EditText timeEndEditTest;
    private EditText timeBeginEditTest;
    private Button addTmpiButton;

    private TmiSpinnerAdapter spinnerAdapter;
    private TmpiListViewAdapter listViewAdapter;

    private int selectedTmi;

    private List<TimeManagerPlanItem> tmpItems;
    private String date;

    @Override
    public void onInit() {
        initViews();
        initSpinner();
        initListView();
        addTmiButton.setOnClickListener(getAddTmiListener());
        addTmpiButton.setOnClickListener(getTmpiListener());
    }

    @SuppressLint("SimpleDateFormat")
    private void initListView() {
        tmpItems = new ArrayList<>();
        listViewAdapter = new TmpiListViewAdapter(activity, R.layout.item_tmpi_list, tmpItems);
        listView.setAdapter(listViewAdapter);
        updateTmpiList(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
    }

    private void updateTmpiList(String date) {
        dateTV.setText(date);
        this.date = date;
        Session session = ((DayManagerApplication) activity.getApplication()).getSession();
        new GetTmpiTask(
                new GetTmpiReadyAction(),
                session.getContext(),
                session.getHost()
        ).execute(date);
    }

    private void initSpinner() {
        DayManagerApplication application = (DayManagerApplication) activity.getApplication();
        spinnerAdapter = new TmiSpinnerAdapter(activity, android.R.layout.simple_spinner_item, application.getSession().getTimeManagerItems());
        tmiSpinner.setAdapter(spinnerAdapter);
        tmiSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTmi = ((TimeManagerItem) adapterView.getSelectedItem()).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedTmi = ((TimeManagerItem) adapterView.getItemAtPosition(0)).getId();
            }
        });
    }

    private View.OnClickListener getTmpiListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Session session = ((DayManagerApplication) activity.getApplication()).getSession();
                String timeBegin = timeBeginEditTest.getText() + "";
                String timeEnd = timeEndEditTest.getText() + "";
                if (TimeValidator.validate(timeBegin, timeEnd)) {
                    new AddTmpiTask(
                            new AddTmpiReadyAction(),
                            session.getContext(),
                            session.getHost()
                    ).execute(new TimeManagerPlanItem(
                            new TimeManagerItem("", selectedTmi),
                            timeBegin,
                            timeEnd,
                            null, DateParser.parseDate(date),
                            0)
                    );
                } else {
                    Toast.makeText(activity, "Validation error!", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private View.OnClickListener getAddTmiListener() {
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
                                    new AddTmiTask(
                                            new AddTmiReadyAction(),
                                            application.getSession().getHost(),
                                            application.getSession().getContext()
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

        listView = activity.findViewById(R.id.planListView);

        addTmiButton = activity.findViewById(R.id.addTmiButton);

        tmiSpinner = activity.findViewById(R.id.tmiSpinner);
        timeEndEditTest = activity.findViewById(R.id.timeEndTextView);
        timeBeginEditTest = activity.findViewById(R.id.timeBeginTextView);
        addTmpiButton = activity.findViewById(R.id.addTmpiButton);
    }

    @Override
    protected void onDateSet(int year, int month, int day) {
        updateTmpiList(year + "-" + (month+1) + "-" + day);
    }

    private class AddTmiReadyAction implements ReadyAction {

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
                application.getSession().getTimeManagerItems().add(new TimeManagerItem(answer.getString("name"), answer.getInt("id")));
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


    private class GetTmpiReadyAction implements ReadyAction {

        @Override
        public void onError() {
            Toast.makeText(activity, "Sorry, smth wrong", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(JSONObject answer) {
            try {
                JSONArray array = answer.getJSONArray("tmpItems");
                List<TimeManagerPlanItem> tmpItems = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonTmpi = array.getJSONObject(i);
                    JSONObject jsonTmi = jsonTmpi.getJSONObject("timeManagerItem");
                    TimeManagerItem tmi = new TimeManagerItem(jsonTmi.getString("name"), jsonTmi.getInt("id"));
                    TimeManagerPlanItem tmpi = new TimeManagerPlanItem(
                            tmi,
                            jsonTmpi.getString("timeBegin"),
                            jsonTmpi.getString("timeEnd"),
                            null,
                            new java.sql.Date(Long.parseLong(
                                    jsonTmpi.getString("date"))),
                            jsonTmpi.getInt("id")
                    );
                    tmpItems.add(tmpi);
                }
                DayPlanPresenter.this.tmpItems.clear();
                DayPlanPresenter.this.tmpItems.addAll(tmpItems);
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

    private class AddTmpiReadyAction implements ReadyAction {
        @Override
        public void onError() {
            Toast.makeText(activity, "Sorry, smth wrong", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onSuccess(JSONObject answer) {
            try {
                JSONObject jsonTmpi = answer.getJSONObject("tmpi");
                JSONObject jsonTmi = jsonTmpi.getJSONObject("timeManagerItem");
                JSONObject jsonUser = jsonTmpi.getJSONObject("user");
                TimeManagerPlanItem tmpi = new TimeManagerPlanItem(
                        new TimeManagerItem(jsonTmi.getString("name"), jsonTmi.getInt("id")),
                        jsonTmpi.getString("timeBegin"),
                        jsonTmpi.getString("timeEnd"),
                        new User(Role.USER, jsonUser.getString("nickname"), jsonUser.getInt("id")),
                        new java.sql.Date(Long.parseLong(jsonTmpi.getString("date"))),
                        jsonTmpi.getInt("id")
                );
                tmpItems.add(tmpi);
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

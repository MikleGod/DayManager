package com.example.mikle.daymanager.presenter.impl;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikle.daymanager.DayManagerApplication;
import com.example.mikle.daymanager.R;
import com.example.mikle.daymanager.internet.AddTmiTask;
import com.example.mikle.daymanager.presenter.MainActivityPresenter;
import com.example.mikle.daymanager.view.MainActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public abstract class BaseMainActivityPresenter implements MainActivityPresenter{


    protected MainActivity activity;

    protected TextView dateTV;

    @Override
    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    public View.OnClickListener getDateTVListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.showDialog(DATE_DIALOG_ID);
            }
        };
    }

    public Dialog getDateDialog(){
        Date date = new Date();
        return new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                BaseMainActivityPresenter.this.onDateSet(i, i1, i2);
            }
        }, date.getYear(), date.getMonth(), date.getDay());
    }

    protected abstract void onDateSet(int year, int month, int day);
}

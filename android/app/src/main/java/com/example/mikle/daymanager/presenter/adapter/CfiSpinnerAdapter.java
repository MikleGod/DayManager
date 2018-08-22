package com.example.mikle.daymanager.presenter.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mikle.daymanager.entity.CashFlowItem;

import java.util.List;

public class CfiSpinnerAdapter extends ArrayAdapter<CashFlowItem> {

    private final List<CashFlowItem> items;

    public CfiSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<CashFlowItem> objects) {
        super(context, resource, objects);
        items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((AppCompatActivity) getContext()).getLayoutInflater();
        View row = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        TextView label = row.findViewById(android.R.id.text1);
        label.setText(items.get(position).getName());
        return row;
    }


}

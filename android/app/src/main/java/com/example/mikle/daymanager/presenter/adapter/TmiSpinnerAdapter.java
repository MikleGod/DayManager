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

import com.example.mikle.daymanager.R;
import com.example.mikle.daymanager.entity.TimeManagerItem;

import java.util.List;

public class TmiSpinnerAdapter extends ArrayAdapter<TimeManagerItem> {


    private final List<TimeManagerItem> items;

    public TmiSpinnerAdapter(@NonNull Context context, int resource, List<TimeManagerItem> items) {
        super(context, resource, items);
        this.items = items;
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

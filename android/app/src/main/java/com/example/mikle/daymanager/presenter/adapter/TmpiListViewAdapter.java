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
import com.example.mikle.daymanager.entity.TimeManagerPlanItem;

import java.util.List;

public class TmpiListViewAdapter extends ArrayAdapter<TimeManagerPlanItem>{


    private final List<TimeManagerPlanItem> items;

    public TmpiListViewAdapter(@NonNull Context context, int resource, @NonNull List<TimeManagerPlanItem> items) {
        super(context, resource, items);
        this.items = items;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((AppCompatActivity) getContext()).getLayoutInflater();
        View row = inflater.inflate(R.layout.item_tmpi_list, parent, false);

        TextView dateTV = row.findViewById(R.id.dateTextView);
        TextView timeBeginTV = row.findViewById(R.id.timeBeginTextView);
        TextView timeEndTV = row.findViewById(R.id.timeEndTextView);
        TextView tmiTV = row.findViewById(R.id.tmiTextView);

        dateTV.setText(items.get(position).getDate().toString());
        timeBeginTV.setText(items.get(position).getTimeBegin());
        timeEndTV.setText(items.get(position).getTimeEnd());
        tmiTV.setText(items.get(position).getTimeManagerItem().getName());

        return row;
    }
}

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
import com.example.mikle.daymanager.entity.dto.StatItem;

import java.util.List;

public class StatLVAdapter extends ArrayAdapter<StatItem> {


    private final List<StatItem> statItems;

    public StatLVAdapter(@NonNull Context context, @NonNull List<StatItem> objects) {
        super(context, R.layout.item_stat_list, objects);
        this.statItems = objects;
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
        View row = inflater.inflate(R.layout.item_stat_list, parent, false);

        TextView nameTV = row.findViewById(R.id.nameTV);
        TextView valueTV = row.findViewById(R.id.valueTV);

        nameTV.setText(statItems.get(position).getName());
        valueTV.setText(statItems.get(position).getValue());

        return row;
    }
}

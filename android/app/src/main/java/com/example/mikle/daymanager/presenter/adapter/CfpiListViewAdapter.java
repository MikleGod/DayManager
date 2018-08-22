package com.example.mikle.daymanager.presenter.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mikle.daymanager.R;
import com.example.mikle.daymanager.entity.CashFlowPlanItem;
import com.example.mikle.daymanager.view.MainActivity;

import java.util.List;

public class CfpiListViewAdapter extends ArrayAdapter<CashFlowPlanItem>{

    private final List<CashFlowPlanItem> items;


    public CfpiListViewAdapter(MainActivity activity, int item_cfpi_list, List<CashFlowPlanItem> cfpItems) {
        super(activity, item_cfpi_list, cfpItems);
        this.items = cfpItems;
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
        View row = inflater.inflate(R.layout.item_cfpi_list, parent, false);

        TextView cfiNameTV = row.findViewById(R.id.cfiName);
        TextView costTv = row.findViewById(R.id.costTV);

        cfiNameTV.setText(items.get(position).getCashFlowItem().getName());
        costTv.setText(items.get(position).getCost()+"");

        return row;
    }
}

package com.example.mikle.daymanager.internet;

import com.example.mikle.daymanager.entity.TimeManagerPlanItem;
import com.example.mikle.daymanager.presenter.action.ReadyAction;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import java.util.ArrayList;
import java.util.List;

public class AddTmpiTask extends RequestTask {

    private String host;

    public AddTmpiTask(ReadyAction context, HttpContext httpContext, String host) {
        super(context, httpContext);
        this.host = host;
    }

    @Override
    protected String createUrl(Object urls) {
        return host;
    }

    @Override
    protected List<NameValuePair> createParams(Object urls) {
        TimeManagerPlanItem item = (TimeManagerPlanItem) urls;

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("date", item.getDate().toString()));
        params.add(new BasicNameValuePair("time_begin", item.getTimeBegin()));
        params.add(new BasicNameValuePair("tmi_id", ""+item.getTimeManagerItem().getId()));
        params.add(new BasicNameValuePair("time_end", item.getTimeEnd()));
        params.add(new BasicNameValuePair("action", "add_tmpi"));
        return params;
    }
}

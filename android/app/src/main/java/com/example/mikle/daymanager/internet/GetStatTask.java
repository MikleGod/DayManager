package com.example.mikle.daymanager.internet;

import com.example.mikle.daymanager.presenter.action.ReadyAction;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import java.util.ArrayList;
import java.util.List;

public class GetStatTask extends RequestTask {


    private final String host;

    public GetStatTask(ReadyAction context, HttpContext httpContext, String host) {
        super(context, httpContext);
        this.host = host;
    }

    @Override
    protected String createUrl(Object urls) {
        return host;
    }

    @Override
    protected List<NameValuePair> createParams(Object urls) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("action", "get_stat"));
        return params;
    }
}

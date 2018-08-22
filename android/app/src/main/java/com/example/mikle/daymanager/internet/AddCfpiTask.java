package com.example.mikle.daymanager.internet;

import com.example.mikle.daymanager.entity.CashFlowPlanItem;
import com.example.mikle.daymanager.presenter.action.ReadyAction;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import java.util.ArrayList;
import java.util.List;

public class AddCfpiTask extends RequestTask {


    private final String host;

    public AddCfpiTask(ReadyAction context, HttpContext httpContext, String host) {
        super(context, httpContext);
        this.host = host;
    }

    @Override
    protected String createUrl(Object urls) {
        return host;
    }

    @Override
    protected List<NameValuePair> createParams(Object urls) {

        CashFlowPlanItem item = (CashFlowPlanItem) urls;

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("date", item.getDate().toString()));
        params.add(new BasicNameValuePair("cost", item.getCost()+""));
        params.add(new BasicNameValuePair("cfi_id", ""+item.getCashFlowItem().getId()));
        params.add(new BasicNameValuePair("action", "add_cfpi"));
        return params;
    }
}

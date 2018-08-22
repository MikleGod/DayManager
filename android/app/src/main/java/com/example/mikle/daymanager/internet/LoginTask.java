package com.example.mikle.daymanager.internet;

import com.example.mikle.daymanager.entity.dto.LoginDto;
import com.example.mikle.daymanager.presenter.action.ReadyAction;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import java.util.ArrayList;
import java.util.List;



public class LoginTask extends RequestTask {

    private static final String PASSWORD = "password";
    private static final String MAIL = "mail";
    private static final String ACTION = "action";
    private static final String LOGIN = "login";

    public LoginTask(ReadyAction context, HttpContext httpContext) {
        super(context, httpContext);
    }

    @Override
    protected String createUrl(Object urls) {
        LoginDto dto = (LoginDto) urls;
        return dto.getHost();
    }

    @Override
    protected List<NameValuePair> createParams(Object urls) {
        LoginDto dto = (LoginDto) urls;


        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(MAIL, dto.getMail()));
        params.add(new BasicNameValuePair(PASSWORD, dto.getPassword()));
        params.add(new BasicNameValuePair(ACTION, LOGIN));
        return params;
    }
}

package com.example.mikle.daymanager.internet;

import android.os.AsyncTask;

import com.example.mikle.daymanager.presenter.action.ReadyAction;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import java.util.List;

public abstract class RequestTask extends AsyncTask<Object, Void, JSONObject> {
    ReadyAction context;
    HttpContext httpContext;

    public RequestTask(ReadyAction context, HttpContext httpContext) {
        super();
        this.context = context;
        this.httpContext = httpContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(Object... urls) {
        return loadJSON(urls[0]);
    }


    public  JSONObject loadJSON(Object urls) {

        RequestMaker jParser = new RequestMaker(httpContext);

        List<NameValuePair> params = createParams(urls);
        params.add(new BasicNameValuePair("json", "true"));

        JSONObject json = jParser.makeHttpRequest("http://"+createUrl(urls)+"/controller", params);

        return json;
    }

    protected abstract String createUrl(Object urls);

    protected abstract List<NameValuePair> createParams(Object urls);

    @Override
    protected void onPostExecute(JSONObject jsonData) {
        if (jsonData != null) {
            super.onPostExecute(jsonData);
            context.onSuccess(jsonData);
        } else {
            context.onError();
        }
    }

}

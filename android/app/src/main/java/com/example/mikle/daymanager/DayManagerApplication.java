package com.example.mikle.daymanager;

import com.example.mikle.daymanager.entity.CashFlowItem;
import com.example.mikle.daymanager.entity.Session;
import com.example.mikle.daymanager.entity.TimeManagerItem;
import com.example.mikle.daymanager.entity.User;

import java.util.List;

public class DayManagerApplication extends android.app.Application {
    private Session session = new Session();

    public Session getSession() {
        return session;
    }
}

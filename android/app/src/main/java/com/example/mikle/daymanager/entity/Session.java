package com.example.mikle.daymanager.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private User user;
    private List<CashFlowItem> cashFlowItems = new ArrayList<>();
    private List<TimeManagerItem> timeManagerItems = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CashFlowItem> getCashFlowItems() {
        return cashFlowItems;
    }

    public void setCashFlowItems(List<CashFlowItem> cashFlowItems) {
        this.cashFlowItems = cashFlowItems;
    }

    public List<TimeManagerItem> getTimeManagerItems() {
        return timeManagerItems;
    }

    public void setTimeManagerItems(List<TimeManagerItem> timeManagerItems) {
        this.timeManagerItems = timeManagerItems;
    }

    @Override
    public String toString() {
        return "Session{" +
                "user=" + user +
                ", cashFlowItems=" + cashFlowItems +
                ", timeManagerItems=" + timeManagerItems +
                '}';
    }
}

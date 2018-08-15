package com.example.mikle.daymanager.entity;

import java.sql.Date;
import java.util.Objects;

public class TimeManagerPlanItem {

    private TimeManagerItem timeManagerItem;
    private String timeBegin;
    private String timeEnd;
    private User user;
    private Date date;
    private int id;

    public TimeManagerPlanItem(TimeManagerItem timeManagerItem, String timeBegin, String timeEnd, User user, Date date, int id) {
        this.timeManagerItem = timeManagerItem;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.user = user;
        this.date = date;
        this.id = id;
    }

    public TimeManagerItem getTimeManagerItem() {
        return timeManagerItem;
    }

    public void setTimeManagerItem(TimeManagerItem timeManagerItem) {
        this.timeManagerItem = timeManagerItem;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeManagerPlanItem that = (TimeManagerPlanItem) o;
        return id == that.id &&
                Objects.equals(timeManagerItem, that.timeManagerItem) &&
                Objects.equals(timeBegin, that.timeBegin) &&
                Objects.equals(timeEnd, that.timeEnd) &&
                Objects.equals(user, that.user) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(timeManagerItem, timeBegin, timeEnd, user, date, id);
    }
}

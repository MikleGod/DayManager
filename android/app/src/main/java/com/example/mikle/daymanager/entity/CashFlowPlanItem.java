package com.example.mikle.daymanager.entity;

import java.sql.Date;
import java.util.Objects;

public class CashFlowPlanItem{
    private int id;
    private CashFlowItem cashFlowItem;
    private double cost;
    private Date date;
    private User user;

    public CashFlowPlanItem(int id, CashFlowItem cashFlowItem, double cost, Date date, User user) {
        this.id = id;
        this.cashFlowItem = cashFlowItem;
        this.cost = cost;
        this.date = date;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CashFlowItem getCashFlowItem() {
        return cashFlowItem;
    }

    public void setCashFlowItem(CashFlowItem cashFlowItem) {
        this.cashFlowItem = cashFlowItem;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashFlowPlanItem that = (CashFlowPlanItem) o;
        return id == that.id &&
                Double.compare(that.cost, cost) == 0 &&
                Objects.equals(cashFlowItem, that.cashFlowItem) &&
                Objects.equals(date, that.date) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, cashFlowItem, cost, date, user);
    }
}

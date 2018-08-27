package com.example.mikle.daymanager.entity.dto;

public class StatItem {
    private String name;
    private String value;

    public StatItem(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StatItem{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}

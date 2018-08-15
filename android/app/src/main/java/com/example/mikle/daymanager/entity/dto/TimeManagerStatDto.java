package com.example.mikle.daymanager.entity.dto;

import com.example.mikle.daymanager.entity.TimeManagerItem;

import java.util.Objects;

public class TimeManagerStatDto {
    private TimeManagerItem tmi;
    private String time;

    public TimeManagerStatDto(TimeManagerItem tmi, String time) {
        this.tmi = tmi;
        this.time = time;
    }

    public TimeManagerItem getTmi() {
        return tmi;
    }

    public void setTmi(TimeManagerItem tmi) {
        this.tmi = tmi;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

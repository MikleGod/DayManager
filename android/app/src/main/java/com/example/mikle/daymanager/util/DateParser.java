package com.example.mikle.daymanager.util;

import android.support.annotation.NonNull;

import java.sql.Date;
import java.time.LocalDate;

public class DateParser {
    public static Date parseDate(@NonNull String dateString) {
        Date date;
        String dateElements[] = dateString.split("-");
        date = new Date(Integer.parseInt(dateElements[0]) - 1900, Integer.parseInt(dateElements[1]) - 1, Integer.parseInt(dateElements[2]));

        return date;
    }
}

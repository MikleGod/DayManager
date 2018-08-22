package com.example.mikle.daymanager.presenter.validator;

public class TimeValidator {

    public static final String TIME_REGEX = ":";
    public static final int HOURS_PART_INDEX = 0;
    public static final int MINUTES_PART_INDEX = 1;
    public static final int MIN_TIME_NUMBER = 0;
    public static final int MAX_HOURS_NUMBER = 24;
    public static final int MIN_MINUTES_NUMBER = 60;

    public static boolean validate(String timeBegin, String timeEnd) {
        try {

            String timeBeginParts[] = timeBegin.split(TIME_REGEX);
            String timeEndParts[] = timeEnd.split(TIME_REGEX);

            int timeBeginHours = Integer.parseInt(timeBeginParts[HOURS_PART_INDEX]);
            int timeBeginMinutes = Integer.parseInt(timeBeginParts[MINUTES_PART_INDEX]);
            int timeEndHours = Integer.parseInt(timeEndParts[0]);
            int timeEndMinutes = Integer.parseInt(timeEndParts[1]);

            return ((timeBeginHours == timeEndHours && timeBeginMinutes < timeEndMinutes) ||
                    timeBeginHours < timeEndHours) &&
                    timeBeginHours >= MIN_TIME_NUMBER && timeBeginHours < MAX_HOURS_NUMBER &&
                    timeEndHours >= MIN_TIME_NUMBER && timeEndHours < MAX_HOURS_NUMBER &&
                    timeBeginMinutes >= MIN_TIME_NUMBER && timeBeginMinutes < MIN_MINUTES_NUMBER &&
                    timeEndMinutes >= MIN_TIME_NUMBER && timeEndMinutes < MIN_MINUTES_NUMBER;
        } catch (Exception e) {
            return false;
        }
    }
}

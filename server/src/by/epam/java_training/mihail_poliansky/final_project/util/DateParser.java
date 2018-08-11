package by.epam.java_training.mihail_poliansky.final_project.util;

import java.sql.Date;
import java.time.LocalDate;

public class DateParser {
    public static Date parseDate(String dateString) {
        Date date;
        if (dateString != null && !dateString.isEmpty()) {
            String dateElements[] = dateString.split("-");
            date = new Date(Integer.parseInt(dateElements[0]) - 1900, Integer.parseInt(dateElements[1]) - 1, Integer.parseInt(dateElements[2]));
        } else {
            date = Date.valueOf(LocalDate.now());
        }
        return date;
    }
}

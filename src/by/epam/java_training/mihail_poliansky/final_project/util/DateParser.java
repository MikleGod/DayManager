package by.epam.java_training.mihail_poliansky.final_project.util;

import java.sql.Date;

public class DateParser {
    public static Date parseDate(String date){
        String dateElements[] = date.split("-");
        return new Date(Integer.parseInt(dateElements[0])-1900, Integer.parseInt(dateElements[1])-1, Integer.parseInt(dateElements[2]));
    }
}

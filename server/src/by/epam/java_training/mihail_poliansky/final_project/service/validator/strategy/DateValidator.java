package by.epam.java_training.mihail_poliansky.final_project.service.validator.strategy;

import java.sql.Date;

import static by.epam.java_training.mihail_poliansky.final_project.service.validator.strategy.ValidationConstant.*;

public class DateValidator implements Validator {


    private static final String DATE_SPLITTER = "-";

    @Override
    public <T> boolean validate(T t) {
        Date date = (Date) t;
        boolean isValid;
        try {
            String[] dateParts = date.toString().split(DATE_SPLITTER);
            isValid = dateParts[0].length() == YEAR_NUMBER_LENGTH && 
                    dateParts[1].length() == MONTH_NUMBER_LENGHT && 
                    dateParts[2].length() == DAY_NUMBER_LENGTH &&
                    Integer.parseInt(dateParts[0]) >= MIN_YEAR_VALUE &&
                    Integer.parseInt(dateParts[0]) <= MAX_YEAR_VALUE &&
                    Integer.parseInt(dateParts[2]) <= MIN_DAY_VALUE &&
                    Integer.parseInt(dateParts[2]) >= MAX_DAY_VALUE &&
                    Integer.parseInt(dateParts[1]) <= MAX_MONTH_VALUE &&
                    Integer.parseInt(dateParts[1]) >= MIN_MONTH_VALUE;
        } catch (Exception e){
            isValid = false;
        }
        return isValid;
    }
}

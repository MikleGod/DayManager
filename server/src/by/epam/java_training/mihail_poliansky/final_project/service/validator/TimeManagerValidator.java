package by.epam.java_training.mihail_poliansky.final_project.service.validator;

import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;

import java.sql.Date;
import java.util.List;

public interface TimeManagerValidator {
    boolean validate(TimeManagerItem item);
    boolean validate(User user);
    boolean validate(TimeManagerPlanItem plan);
    boolean validate(List<TimeManagerPlanItem> plan);
    boolean validate(Date date);
}

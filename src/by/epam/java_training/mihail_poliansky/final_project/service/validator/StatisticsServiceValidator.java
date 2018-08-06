package by.epam.java_training.mihail_poliansky.final_project.service.validator;

import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;

public interface StatisticsServiceValidator {
    boolean validate(User user);

    boolean validate(TimeManagerItem item);

    boolean validate(CashFlowItem item);
}

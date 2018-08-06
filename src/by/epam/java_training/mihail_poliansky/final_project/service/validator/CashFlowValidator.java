package by.epam.java_training.mihail_poliansky.final_project.service.validator;

import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;

import java.sql.Date;
import java.util.List;

public interface CashFlowValidator {
    boolean validate(CashFlowItem item);

    boolean validate(User user);

    boolean validate(Date date);

    boolean validate(List<CashFlowPlanItem> plan);

    boolean validate(CashFlowPlanItem plan);
}

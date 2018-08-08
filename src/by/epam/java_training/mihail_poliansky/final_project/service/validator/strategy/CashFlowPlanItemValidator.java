package by.epam.java_training.mihail_poliansky.final_project.service.validator.strategy;

import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowPlanItem;

public class CashFlowPlanItemValidator implements Validator {
    @Override
    public <T> boolean validate(T t) {
        CashFlowPlanItem item = (CashFlowPlanItem) t;
        return true;
    }
}

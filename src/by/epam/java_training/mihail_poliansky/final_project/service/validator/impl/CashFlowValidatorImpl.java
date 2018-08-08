package by.epam.java_training.mihail_poliansky.final_project.service.validator.impl;

import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.CashFlowValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.strategy.StrategyValidatorFactory;

import java.sql.Date;
import java.util.List;

public class CashFlowValidatorImpl implements CashFlowValidator {
    @Override
    public boolean validate(CashFlowItem item) {
        return StrategyValidatorFactory.getCashFlowItemValidator().validate(item);
    }

    @Override
    public boolean validate(User user) {
        return StrategyValidatorFactory.getUserValidator().validate(user);
    }

    @Override
    public boolean validate(Date date) {
        return StrategyValidatorFactory.getDateValidator().validate(date);
    }

    @Override
    public boolean validate(List<CashFlowPlanItem> plan) {
        for (CashFlowPlanItem item : plan) {
            if (!StrategyValidatorFactory.getCashFlowPlanItemValidator().validate(item)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean validate(CashFlowPlanItem plan) {
        return StrategyValidatorFactory.getCashFlowPlanItemValidator().validate(plan);
    }
}

package by.epam.java_training.mihail_poliansky.final_project.service.validator.impl;

import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.TimeManagerValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.strategy.StrategyValidatorFactory;

import java.sql.Date;
import java.util.List;

public class TimeManagerValidatorImpl implements TimeManagerValidator {
    @Override
    public boolean validate(TimeManagerItem item) {
        return StrategyValidatorFactory.getTimeManagerItemValidator().validate(item);
    }

    @Override
    public boolean validate(User user) {
        return StrategyValidatorFactory.getUserValidator().validate(user);
    }

    @Override
    public boolean validate(TimeManagerPlanItem plan) {
        return StrategyValidatorFactory.getTimeManagerPlanItemValidator().validate(plan);
    }

    @Override
    public boolean validate(List<TimeManagerPlanItem> plan) {
        for (TimeManagerPlanItem item : plan) {
            if (!StrategyValidatorFactory.getTimeManagerPlanItemValidator().validate(item)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean validate(Date date) {
        return StrategyValidatorFactory.getDateValidator().validate(date);
    }
}

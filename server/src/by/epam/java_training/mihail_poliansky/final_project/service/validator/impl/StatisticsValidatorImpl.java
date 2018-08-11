package by.epam.java_training.mihail_poliansky.final_project.service.validator.impl;

import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.StatisticsServiceValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.strategy.StrategyValidatorFactory;

public class StatisticsValidatorImpl implements StatisticsServiceValidator {
    @Override
    public boolean validate(User user) {
        return StrategyValidatorFactory.getUserValidator().validate(user);
    }

    @Override
    public boolean validate(TimeManagerItem item) {
        return StrategyValidatorFactory.getTimeManagerItemValidator().validate(item);
    }

    @Override
    public boolean validate(CashFlowItem item) {
        return StrategyValidatorFactory.getCashFlowItemValidator().validate(item);
    }
}

package by.epam.java_training.mihail_poliansky.final_project.service.validator.impl;

import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.entity.UserPrivates;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.AuthValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.strategy.StrategyValidatorFactory;

public class AuthValidatorImpl implements AuthValidator {
    @Override
    public boolean validate(UserPrivates privates) {
        return StrategyValidatorFactory.getUserPrivatesValidator().validate(privates);
    }

    @Override
    public boolean validate(User user) {
        return StrategyValidatorFactory.getUserValidator().validate(user);
    }
}

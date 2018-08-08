package by.epam.java_training.mihail_poliansky.final_project.service.validator.strategy;

import by.epam.java_training.mihail_poliansky.final_project.entity.User;

public class UserValidator implements Validator {
    @Override
    public <T> boolean validate(T t) {
        User user = (User) t;
        return user.getNickname() != null && !user.getNickname().isEmpty();
    }
}

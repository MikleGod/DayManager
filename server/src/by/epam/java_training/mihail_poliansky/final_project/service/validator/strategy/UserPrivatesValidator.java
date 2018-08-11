package by.epam.java_training.mihail_poliansky.final_project.service.validator.strategy;

import by.epam.java_training.mihail_poliansky.final_project.entity.UserPrivates;

public class UserPrivatesValidator implements Validator{
    @Override
    public <T> boolean validate(T t) {
        UserPrivates privates = (UserPrivates) t;
        return privates.getMail() != null &&
                !privates.getMail().isEmpty() &&
                privates.getPassword() != 0;
    }
}

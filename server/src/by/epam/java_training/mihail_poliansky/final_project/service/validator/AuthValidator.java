package by.epam.java_training.mihail_poliansky.final_project.service.validator;

import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.entity.UserPrivates;

public interface AuthValidator {
    boolean validate(UserPrivates privates);
    boolean validate(User user);
}


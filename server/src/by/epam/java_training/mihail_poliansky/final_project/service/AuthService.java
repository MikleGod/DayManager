package by.epam.java_training.mihail_poliansky.final_project.service;

import by.epam.java_training.mihail_poliansky.final_project.entity.ActivityEnum;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.entity.UserPrivates;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchUserException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.UserAlreadyExistsException;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.ServiceValidationException;

import java.util.List;

public interface AuthService {
    User login(UserPrivates user) throws NoSuchUserException, ServiceException;
    void registration(User user, UserPrivates privates) throws ServiceException, UserAlreadyExistsException;
    List<ActivityEnum> findActivities(User user) throws ServiceException;
    boolean isUserExists(String mail) throws ServiceException;
}

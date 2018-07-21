package by.epam.java_training.mihail_poliansky.final_project.service;

import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.entity.UserPrivates;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchUserException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.UserAlreadyExistsException;

public interface AuthService {
    User login(UserPrivates user) throws NoSuchUserException, ServiceException;
    void registration(User user, UserPrivates privates) throws ServiceException, UserAlreadyExistsException;
    boolean isUserExists(String mail) throws ServiceException;
}

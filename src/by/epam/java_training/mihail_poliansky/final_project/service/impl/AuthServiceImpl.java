package by.epam.java_training.mihail_poliansky.final_project.service.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.impl.DaoFactory;
import by.epam.java_training.mihail_poliansky.final_project.dao.UserPrivatesDao;
import by.epam.java_training.mihail_poliansky.final_project.dao.UsersDao;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.entity.UserPrivates;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchUserException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.UserAlreadyExistsException;
import by.epam.java_training.mihail_poliansky.final_project.service.AuthService;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.AuthValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.ServiceValidationException;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.impl.ServiceValidatorFactory;

public class AuthServiceImpl implements AuthService {


    private AuthValidator authValidator = ServiceValidatorFactory.getAuthValidator();

    @Override
    public User login(UserPrivates userPrivates) throws NoSuchUserException, ServiceException {
        if (authValidator.validate(userPrivates)) {
            try {
                UserPrivates privates = DaoFactory.getUserPrivatesDao().find(userPrivates);
                return DaoFactory.getUsersDao().find(privates.getId());
            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(userPrivates.toString());
    }

    @Override
    public void registration(User user, UserPrivates privates) throws ServiceException, UserAlreadyExistsException {
        if (authValidator.validate(user)) {
            try {
                UsersDao usersDao = DaoFactory.getUsersDao();
                UserPrivatesDao privatesDao = DaoFactory.getUserPrivatesDao();
                try {
                    privatesDao.find(privates);
                    throw new UserAlreadyExistsException();
                } catch (NoSuchUserException e) {
                    e.printStackTrace();
                }
                privatesDao.insert(privates);
                try {
                    user.setId(privatesDao.find(privates).getId());
                } catch (NoSuchUserException e) {
                    e.printStackTrace();
                }
                usersDao.insert(user);
            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(privates.toString() + " ... " + user.toString());
    }

    @Override
    public boolean isUserExists(String mail) throws ServiceException {
        try {
            return DaoFactory.getUserPrivatesDao().find(mail);
        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }
}

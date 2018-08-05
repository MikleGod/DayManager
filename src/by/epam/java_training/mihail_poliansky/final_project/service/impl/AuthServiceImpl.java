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
import by.epam.java_training.mihail_poliansky.final_project.factory.EntityFactory;
import by.epam.java_training.mihail_poliansky.final_project.service.AuthService;
import org.junit.Test;

public class AuthServiceImpl implements AuthService {

    //TODO

    @Override
    public User login(UserPrivates user) throws NoSuchUserException, ServiceException {
        try {
            UserPrivates privates = DaoFactory.getUserPrivatesDao().find(user);
            return DaoFactory.getUsersDao().find(privates.getId());
        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void registration(User user, UserPrivates privates) throws ServiceException, UserAlreadyExistsException {
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
    }

    @Override
    public boolean isUserExists(String mail) throws ServiceException {
        try {
            return DaoFactory.getUserPrivatesDao().find(mail);
        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Test
    public void testRegistration() {
        try {
            registration(
                    EntityFactory.createUser(null, "test5"),
                    EntityFactory.createUserPrivates("test5@gmail.com", "test5".hashCode()));
        } catch (ServiceException | UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}

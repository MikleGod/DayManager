package by.epam.java_training.mihail_poliansky.final_project.service.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.Impl.DaoFactory;
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
        UserPrivates privates = DaoFactory.getUserPrivatesDao().find(user);
        return DaoFactory.getUsersDao().find(privates.getId());
    }

    @Override
    public boolean registration(User user, UserPrivates privates) throws ServiceException, UserAlreadyExistsException {
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
        return true;
    }

    @Test
    public void testRegistration(){
        try {
            registration(
                    EntityFactory.createUser(null, "test5"),
                    EntityFactory.createUserPrivates("test5@gmail.com", "test5".hashCode()));
        } catch (ServiceException | UserAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}

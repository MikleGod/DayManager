package by.epam.java_training.mihail_poliansky.final_project.service.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.*;
import by.epam.java_training.mihail_poliansky.final_project.dao.impl.DaoFactory;
import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchUserException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.UserAlreadyExistsException;
import by.epam.java_training.mihail_poliansky.final_project.service.AuthService;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.AuthValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.ServiceValidationException;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.impl.ServiceValidatorFactory;
import jdk.nashorn.internal.runtime.logging.DebugLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AuthServiceImpl implements AuthService {


    private AuthValidator authValidator = ServiceValidatorFactory.getAuthValidator();
    private Logger logger = LogManager.getLogger(AuthServiceImpl.class);

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
                ActivitiesDao activitiesDao = DaoFactory.getActivitiesDao();
                TimeManagerItemsDao timeManagerItemsDao = DaoFactory.getTimeManagerItemsDao();
                CashFlowItemsDao cashFlowItemsDao = DaoFactory.getCashFlowItemsDao();
                try {
                    privatesDao.find(privates);
                    throw new UserAlreadyExistsException();
                } catch (NoSuchUserException e) {
                    logger.info(e.toString());
                }//todo исправить быдлокод

                privatesDao.insert(privates);

                try {
                    user.setId(privatesDao.find(privates).getId());
                } catch (NoSuchUserException e) {
                    logger.info(e.toString());
                    //в данных условиях получение этого исключения невозможно
                    //только если предыдущая операция была неуспешна
                    //return;
                }

                usersDao.insert(user);
                for (ActivityEnum value : ActivityEnum.values()) {
                    activitiesDao.insert(user, value);
                }

                for (BaseCfi value : BaseCfi.values()) {
                    cashFlowItemsDao.addItem(user, value.getItem());
                }

                for (BaseTmi value : BaseTmi.values()) {
                    timeManagerItemsDao.addItem(user, value.getItem());
                }

            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(privates.toString() + " ... " + user.toString());
    }

    @Override
    public List<ActivityEnum> findActivities(User user) throws ServiceException {
        try {
            return DaoFactory.getActivitiesDao().findActivities(user);
        } catch (DBException | ConnectionPoolException e) {
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
}

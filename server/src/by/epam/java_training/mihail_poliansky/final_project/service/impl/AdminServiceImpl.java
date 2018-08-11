package by.epam.java_training.mihail_poliansky.final_project.service.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.ActivitiesDao;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.impl.DaoFactory;
import by.epam.java_training.mihail_poliansky.final_project.entity.ActivityEnum;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.entity.UserActivitiesDto;
import by.epam.java_training.mihail_poliansky.final_project.service.AdminService;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public List<UserActivitiesDto> findAllUsersAndActs() throws ServiceException {
        List<UserActivitiesDto> dtos = new ArrayList<>();
        try {
            List<User> users = DaoFactory.getUsersDao().findAll();
            ActivitiesDao activitiesDao = DaoFactory.getActivitiesDao();

            for (User user : users) {
                dtos.add(new UserActivitiesDto(user, activitiesDao.findActivities(user)));
            }
        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
        return dtos;
    }

    @Override
    public List<ActivityEnum> findAllActivities() throws ServiceException {
        try {
            return DaoFactory.getActivitiesDao().findAll();
        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addActivity(User user, ActivityEnum activity) throws ServiceException {
        try {
            ActivitiesDao activitiesDao = DaoFactory.getActivitiesDao();
            if (!activitiesDao.findActivities(user).contains(activity)) {
                activitiesDao.insert(user, activity);
            }
        } catch (DBException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteActivity(User user, ActivityEnum activity) throws ServiceException {
        try {
            ActivitiesDao activitiesDao = DaoFactory.getActivitiesDao();
            if (activitiesDao.findActivities(user).contains(activity)) {
                activitiesDao.delete(user, activity);
            }
        } catch (DBException | ConnectionPoolException e) {
            throw new ServiceException(e);
        }
    }
}

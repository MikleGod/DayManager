package by.epam.java_training.mihail_poliansky.final_project.service.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.impl.DaoFactory;
import by.epam.java_training.mihail_poliansky.final_project.dao.TimeManagerDao;
import by.epam.java_training.mihail_poliansky.final_project.dao.TimeManagerItemsDao;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchItemException;
import by.epam.java_training.mihail_poliansky.final_project.service.TimeManagerService;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public class TimeManagerServiceImpl implements TimeManagerService {
    //TODO
    @Override
    public TimeManagerItem changeTMItem(User user, TimeManagerItem item) throws ServiceException {
        TimeManagerItemsDao itemsDao = DaoFactory.getTimeManagerItemsDao();
        TimeManagerItem item1;
        try {
            try {
                item1 = itemsDao.find(item);
            } catch (NoSuchItemException e) {
                itemsDao.insert(item);
                item1 = itemsDao.find(item);
            }
            itemsDao.deleteItem(user, item);
            itemsDao.addItem(user, item1);
            return item1;
        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public TimeManagerItem addTMItem(User user, TimeManagerItem item) throws ServiceException {
        TimeManagerItemsDao itemsDao = DaoFactory.getTimeManagerItemsDao();
        TimeManagerItem item1;
        try {
            try {
                item1 = itemsDao.find(item);
            } catch (NoSuchItemException e) {
                itemsDao.insert(item);
                item1 = itemsDao.find(item);
            }
            itemsDao.addItem(user, item1);
            return item1;

        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteTMItem(User user, TimeManagerItem item) throws ServiceException {
        try {
            DaoFactory.getTimeManagerItemsDao().deleteItem(user, item);

        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TimeManagerItem> getTMItems(User user) throws ServiceException {
        try {
            return DaoFactory.getTimeManagerItemsDao().findAll(user);

        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TimeManagerPlanItem> getPlan(User user, Date date) throws ServiceException {
        try {
            return DaoFactory.getTimeManagerDao().findAll(date, user);

        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deletePlan(User user, Date date) throws ServiceException {
        try {
            TimeManagerDao dao = DaoFactory.getTimeManagerDao();
            List<TimeManagerPlanItem> items = dao.findAll(date, user);
            for (TimeManagerPlanItem item : items) {
                dao.delete(item);
            }

        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TimeManagerPlanItem> changePlan(List<TimeManagerPlanItem> plan) throws ServiceException {
        TimeManagerDao dao = DaoFactory.getTimeManagerDao();
        try {
            for (TimeManagerPlanItem item : plan) {
                dao.delete(item);
            }
            return dao.findAll(plan.get(0).getDate(), plan.get(0).getUser());

        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<TimeManagerPlanItem> addPlan(List<TimeManagerPlanItem> plan) throws ServiceException {
        try {
            TimeManagerDao dao = DaoFactory.getTimeManagerDao();
            for (TimeManagerPlanItem item : plan) {
                dao.insert(item);
            }
            return plan;

        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }
}

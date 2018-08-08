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
import by.epam.java_training.mihail_poliansky.final_project.service.validator.ServiceValidationException;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.TimeManagerValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.impl.ServiceValidatorFactory;

import java.sql.Date;
import java.util.List;

public class TimeManagerServiceImpl implements TimeManagerService {

    public static final String EXCEPTION_MESSAGE_SEPARATOR = " ... ";
    TimeManagerValidator timeManagerValidator = ServiceValidatorFactory.getTimeManagerValidator();

    @Override
    public TimeManagerItem changeTMItem(User user, TimeManagerItem item) throws ServiceException {
        if (timeManagerValidator.validate(item) && timeManagerValidator.validate(user)) {
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
        } else throw new ServiceValidationException(item.toString() + EXCEPTION_MESSAGE_SEPARATOR + user.toString());
    }

    @Override
    public TimeManagerItem addTMItem(User user, TimeManagerItem item) throws ServiceException {
        if (timeManagerValidator.validate(item) && timeManagerValidator.validate(user)) {

            try {
                TimeManagerItemsDao itemsDao = DaoFactory.getTimeManagerItemsDao();

                List<TimeManagerItem> items = itemsDao.findAll(user);
                for (TimeManagerItem cashFlowItem : items) {
                    if (cashFlowItem.getName().equals(item.getName())) {
                        throw new ServiceException("Already has this item");
                    }
                }

                TimeManagerItem item1;
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
        } else throw new ServiceValidationException(item.toString() + EXCEPTION_MESSAGE_SEPARATOR + user.toString());
    }

    @Override
    public void deleteTMItem(User user, TimeManagerItem item) throws ServiceException {
        if (timeManagerValidator.validate(item) && timeManagerValidator.validate(user)) {

            try {
                DaoFactory.getTimeManagerItemsDao().deleteItem(user, item);

            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(user.toString() + EXCEPTION_MESSAGE_SEPARATOR + item.toString());
    }

    @Override
    public List<TimeManagerItem> getTMItems(User user) throws ServiceException {
        if (timeManagerValidator.validate(user)) {
            try {
                return DaoFactory.getTimeManagerItemsDao().findAll(user);

            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(user.toString());
    }

    @Override
    public List<TimeManagerPlanItem> getPlan(User user, Date date) throws ServiceException {
        if (timeManagerValidator.validate(user) && timeManagerValidator.validate(date)) {
            try {
                return DaoFactory.getTimeManagerDao().findAll(date, user);

            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(user.toString() + EXCEPTION_MESSAGE_SEPARATOR + date);
    }

    @Override
    public void deletePlan(User user, Date date) throws ServiceException {
        if (timeManagerValidator.validate(user) && timeManagerValidator.validate(date)) {

            try {
                TimeManagerDao dao = DaoFactory.getTimeManagerDao();
                List<TimeManagerPlanItem> items = dao.findAll(date, user);
                for (TimeManagerPlanItem item : items) {
                    dao.delete(item);
                }

            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }

        } else throw new ServiceValidationException(user.toString() + EXCEPTION_MESSAGE_SEPARATOR + date);
    }

    @Override
    public List<TimeManagerPlanItem> changePlan(List<TimeManagerPlanItem> plan) throws ServiceException {
        if (timeManagerValidator.validate(plan)) {

            TimeManagerDao dao = DaoFactory.getTimeManagerDao();
            try {
                for (TimeManagerPlanItem item : plan) {
                    dao.delete(item);
                }
                return dao.findAll(plan.get(0).getDate(), plan.get(0).getUser());

            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(plan.toArray().toString());
    }

    @Override
    public List<TimeManagerPlanItem> addPlan(List<TimeManagerPlanItem> plan) throws ServiceException {
        if (timeManagerValidator.validate(plan)) {

            try {
                TimeManagerDao dao = DaoFactory.getTimeManagerDao();
                for (TimeManagerPlanItem item : plan) {
                    dao.insert(item);
                }
                return plan;

            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(plan.toString());
    }

    @Override
    public TimeManagerPlanItem addPlan(TimeManagerPlanItem plan) throws ServiceException {
        if (timeManagerValidator.validate(plan)) {

            try {
                TimeManagerDao dao = DaoFactory.getTimeManagerDao();
                dao.insert(plan);
                return plan;

            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(plan.toString());

    }

}

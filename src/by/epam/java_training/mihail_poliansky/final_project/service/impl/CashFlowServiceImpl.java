package by.epam.java_training.mihail_poliansky.final_project.service.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.CashFlowDao;
import by.epam.java_training.mihail_poliansky.final_project.dao.CashFlowItemsDao;
import by.epam.java_training.mihail_poliansky.final_project.dao.impl.DaoFactory;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchItemException;
import by.epam.java_training.mihail_poliansky.final_project.service.CashFlowService;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public class CashFlowServiceImpl implements CashFlowService {

    @Override
    public CashFlowItem changeCFItem(User user, CashFlowItem item) throws ServiceException {
        try {
            CashFlowItemsDao itemsDao = DaoFactory.getCashFlowItemsDao();
            CashFlowItem item1;
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
    public CashFlowItem addCFItem(User user, CashFlowItem item) throws ServiceException {
        try {

            CashFlowItemsDao itemsDao = DaoFactory.getCashFlowItemsDao();
            CashFlowItem item1;
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
    public void deleteCFItem(User user, CashFlowItem item) throws ServiceException {
        try {
            DaoFactory.getCashFlowItemsDao().deleteItem(user, item);

        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CashFlowItem> getCFItems(User user) throws ServiceException {
        try {
            return DaoFactory.getCashFlowItemsDao().findAll(user);
        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CashFlowPlanItem> getCF(User user, Date date) throws ServiceException {
        try {
            return DaoFactory.getCashFlowDao().findAll(date, user);
        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteCF(User user, Date date) throws ServiceException {
        try {
            CashFlowDao dao = DaoFactory.getCashFlowDao();
            List<CashFlowPlanItem> items = DaoFactory.getCashFlowDao().findAll(date, user);
            for (CashFlowPlanItem item : items) {
                dao.delete(item);
            }
        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CashFlowPlanItem> changeCF(List<CashFlowPlanItem> plan) throws ServiceException {
        try {
            CashFlowDao dao = DaoFactory.getCashFlowDao();
            for (CashFlowPlanItem cashFlowPlanItem : plan) {
                dao.update(cashFlowPlanItem);
            }
            return dao.findAll(plan.get(0).getDate(), plan.get(0).getUser());
        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<CashFlowPlanItem> addCF(List<CashFlowPlanItem> plan) throws ServiceException {
        try {
            CashFlowDao dao = DaoFactory.getCashFlowDao();
            for (CashFlowPlanItem cashFlowPlanItem : plan) {
                dao.insert(cashFlowPlanItem);
            }
            return plan;
        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
    }
}

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
import by.epam.java_training.mihail_poliansky.final_project.service.validator.CashFlowValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.ServiceValidationException;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.impl.ServiceValidatorFactory;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static by.epam.java_training.mihail_poliansky.final_project.service.impl.TimeManagerServiceImpl.EXCEPTION_MESSAGE_SEPARATOR;

public class CashFlowServiceImpl implements CashFlowService {

    private CashFlowValidator cashFlowValidator = ServiceValidatorFactory.getCashFlowValidator();

    @Override
    public CashFlowItem changeCFItem(User user, CashFlowItem item) throws ServiceException {
        if (cashFlowValidator.validate(item) && cashFlowValidator.validate(user)) {//TODO DTO
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
        } else throw new ServiceValidationException(item.toString());
    }

    @Override
    public CashFlowItem addCFItem(User user, CashFlowItem item) throws ServiceException {
        if (cashFlowValidator.validate(item) && cashFlowValidator.validate(user)) {

            try {

                CashFlowItemsDao itemsDao = DaoFactory.getCashFlowItemsDao();
                List<CashFlowItem> items = itemsDao.findAll(user);
                for (CashFlowItem cashFlowItem : items) {
                    if (cashFlowItem.getName().equals(item.getName())){
                        throw new ServiceException("Already has this item");
                    }
                }

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
        } else throw new ServiceValidationException(item.toString());

    }

    @Override
    public void deleteCFItem(User user, CashFlowItem item) throws ServiceException {
        if (cashFlowValidator.validate(user) && cashFlowValidator.validate(item)) {
            try {
                DaoFactory.getCashFlowItemsDao().deleteItem(user, item);

            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(user.toString() + EXCEPTION_MESSAGE_SEPARATOR + item.toString());
    }

    @Override
    public List<CashFlowItem> getCFItems(User user) throws ServiceException {
        if (cashFlowValidator.validate(user)) {

            try {
                return DaoFactory.getCashFlowItemsDao().findAll(user);
            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(user.toString());


    }

    @Override
    public List<CashFlowPlanItem> getCF(User user, Date date) throws ServiceException {
        if (cashFlowValidator.validate(user) && cashFlowValidator.validate(date)) {

            try {
                return DaoFactory.getCashFlowDao().findAll(date, user);
            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(user.toString() + EXCEPTION_MESSAGE_SEPARATOR + date.toString());

    }

    @Override
    public void deleteCF(User user, Date date) throws ServiceException {
        if (cashFlowValidator.validate(user) && cashFlowValidator.validate(date)) {

            try {
                CashFlowDao dao = DaoFactory.getCashFlowDao();
                List<CashFlowPlanItem> items = DaoFactory.getCashFlowDao().findAll(date, user);
                for (CashFlowPlanItem item : items) {
                    dao.delete(item);
                }
            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(user.toString() + EXCEPTION_MESSAGE_SEPARATOR + date.toString());

    }

    @Override
    public List<CashFlowPlanItem> changeCF(List<CashFlowPlanItem> plan) throws ServiceException {
        if (cashFlowValidator.validate(plan)) {

            try {
                CashFlowDao dao = DaoFactory.getCashFlowDao();
                for (CashFlowPlanItem cashFlowPlanItem : plan) {
                    dao.update(cashFlowPlanItem);
                }
                return dao.findAll(plan.get(0).getDate(), plan.get(0).getUser());
            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(Arrays.toString(plan.toArray()));

    }

    @Override
    public List<CashFlowPlanItem> addCF(List<CashFlowPlanItem> plan) throws ServiceException {
        if (cashFlowValidator.validate(plan)) {

            try {
                CashFlowDao dao = DaoFactory.getCashFlowDao();
                for (CashFlowPlanItem cashFlowPlanItem : plan) {
                    dao.insert(cashFlowPlanItem);
                }
                return plan;
            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }
        } else throw new ServiceValidationException(Arrays.toString(plan.toArray()));

    }

    @Override
    public CashFlowPlanItem addCF(CashFlowPlanItem plan) throws ServiceException {
        if (cashFlowValidator.validate(plan)) {

            try {
            CashFlowDao dao = DaoFactory.getCashFlowDao();
            dao.insert(plan);

            return plan;
        } catch (ConnectionPoolException | DBException e) {
            throw new ServiceException(e);
        }
        } else throw new ServiceValidationException(plan.toString());

    }
}

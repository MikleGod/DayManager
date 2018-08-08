package by.epam.java_training.mihail_poliansky.final_project.service.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.dao.TimeManagerDao;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.impl.DaoFactory;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.StatisticsService;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.ServiceValidationException;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.StatisticsServiceValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.impl.ServiceValidatorFactory;

import java.util.List;

public class StatisticsServiceImpl implements StatisticsService {
    private StatisticsServiceValidator statisticsServiceValidator = ServiceValidatorFactory.getStatisticsServiceValidator();

    @Override
    public String countAllSpentTime(User user) throws ServiceException {
        if (statisticsServiceValidator.validate(user)) {

            TimeManagerDao timeManagerDao = DaoFactory.getTimeManagerDao();

            try {
                List<TimeManagerPlanItem> items = timeManagerDao.findAll(user);

                //TODO


            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }

            return "25-30";
        }
        throw new ServiceValidationException(user.toString());
    }

    @Override
    public double countAllSpentMoney(User user) throws ServiceException {
        if (statisticsServiceValidator.validate(user)) {

            return 45;
        }
        throw new ServiceValidationException(user.toString());

    }

    @Override
    public double countSpentTimeOn(TimeManagerItem item, User user) throws ServiceException {
        if (statisticsServiceValidator.validate(user) && statisticsServiceValidator.validate(item)) {

            return 0;
        }
        throw new ServiceValidationException(user.toString());

    }

    @Override
    public double countSpentMoneyOn(CashFlowItem item, User user) throws ServiceException {
        if (statisticsServiceValidator.validate(user) && statisticsServiceValidator.validate(item)) {

            return 0;
        }
        throw new ServiceValidationException(user.toString());

    }
}

package by.epam.java_training.mihail_poliansky.final_project.service.impl;

import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.StatisticsService;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.ServiceValidationException;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.StatisticsServiceValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.impl.ValidatorFactory;

public class StatisticsServiceImpl implements StatisticsService {
    StatisticsServiceValidator statisticsServiceValidator = ValidatorFactory.getStatisticsServiceValidator();

    @Override
    public double countAllSpentTime(User user) throws ServiceException {
        if (statisticsServiceValidator.validate(user)) {
            return 0;
        }
        throw new ServiceValidationException(user.toString());
    }

    @Override
    public double countAllSpentMoney(User user) throws ServiceException {
        if (statisticsServiceValidator.validate(user)) {

            return 0;
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

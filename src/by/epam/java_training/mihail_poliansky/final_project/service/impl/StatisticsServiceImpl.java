package by.epam.java_training.mihail_poliansky.final_project.service.impl;

import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.StatisticsService;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;

public class StatisticsServiceImpl implements StatisticsService {

    //TODO
    @Override
    public double countAllSpentTime(User user) throws ServiceException {
        return 0;
    }

    @Override
    public double countAllSpentMoney(User user) throws ServiceException {
        return 0;
    }

    @Override
    public double countSpentTimeOn(TimeManagerItem item, User user) throws ServiceException {
        return 0;
    }

    @Override
    public double countSpentMoneyOn(CashFlowItem item, User user) throws ServiceException{
        return 0;
    }
}

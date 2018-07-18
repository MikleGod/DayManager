package by.epam.java_training.mihail_poliansky.final_project.service;

import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;

public interface StatisticsService {

    double countAllSpentTime(User user) throws ServiceException;
    double countAllSpentMoney(User user) throws ServiceException;

    double countSpentTimeOn(TimeManagerItem item, User user) throws ServiceException;
    double countSpentMoneyOn(CashFlowItem item, User user) throws ServiceException;
}

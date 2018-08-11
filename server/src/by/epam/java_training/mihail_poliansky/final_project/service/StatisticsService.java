package by.epam.java_training.mihail_poliansky.final_project.service;

import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public interface StatisticsService {

    List<TimeManagerStatDto> countSpentTime(User user, Date from, Date to) throws ServiceException;
    List<TimeManagerStatDto> countSpentTime(User user) throws ServiceException;

    List<CashFlowStatDto> countSpentMoney(User user, Date from, Date to) throws ServiceException;
    List<CashFlowStatDto> countSpentMoney(User user) throws ServiceException;

}

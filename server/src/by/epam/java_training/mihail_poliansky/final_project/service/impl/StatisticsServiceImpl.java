package by.epam.java_training.mihail_poliansky.final_project.service.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.*;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.impl.DaoFactory;
import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import by.epam.java_training.mihail_poliansky.final_project.service.StatisticsService;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.ServiceValidationException;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.StatisticsServiceValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.impl.ServiceValidatorFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class StatisticsServiceImpl implements StatisticsService {
    private StatisticsServiceValidator statisticsServiceValidator = ServiceValidatorFactory.getStatisticsServiceValidator();

    @Override
    public List<TimeManagerStatDto> countSpentTime(User user, Date from, Date to) throws ServiceException {
        return null;
    }

    @Override
    public List<TimeManagerStatDto> countSpentTime(User user) throws ServiceException {
        if (statisticsServiceValidator.validate(user)) {

            TimeManagerDao timeManagerDao = DaoFactory.getTimeManagerDao();
            TimeManagerItemsDao timeManagerItemsDao = DaoFactory.getTimeManagerItemsDao();
            List<TimeManagerStatDto> timeManagerStatDtos = new ArrayList<>();

            try {
                List<TimeManagerItem> items = timeManagerItemsDao.findAll(user);

                for (TimeManagerItem item : items) {

                    List<TimeManagerPlanItem> planItems = timeManagerDao.findAll(user, item);


                    timeManagerStatDtos.add(new TimeManagerStatDto(item, countSpentTime(planItems)));
                }

            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }

            return timeManagerStatDtos;
        }
        throw new ServiceValidationException(user.toString());
    }

    @Override
    public List<CashFlowStatDto> countSpentMoney(User user, Date from, Date to) throws ServiceException {
        return null;
    }

    private String countSpentTime(List<TimeManagerPlanItem> planItems) {
        int hours = 0, minutes = 0;
        for (TimeManagerPlanItem planItem : planItems) {
            String[] timeBegin = planItem.getTimeBegin().replace(":", "-").split("-");
            String[] timeEnd = planItem.getTimeEnd().replace(":", "-").split("-");
            int timeBeginHours = Integer.parseInt(timeBegin[0]);
            int timeEndHours = Integer.parseInt(timeEnd[0]);
            int timeBeginMinutes = Integer.parseInt(timeBegin[1]);
            int timeEndMinutes = Integer.parseInt(timeEnd[1]);

            hours += timeEndHours - timeBeginHours;
            minutes += timeEndMinutes - timeBeginMinutes;
        }

        int minHours = minutes/60;

        minutes -= minHours * 60;

        hours += minHours;

        return hours+ "-"+minutes;
    }

    @Override
    public List<CashFlowStatDto> countSpentMoney(User user) throws ServiceException {
        if (statisticsServiceValidator.validate(user)) {

            CashFlowDao cashFlowDao = DaoFactory.getCashFlowDao();
            CashFlowItemsDao cashFlowItemsDao = DaoFactory.getCashFlowItemsDao();
            List<CashFlowStatDto> cashFlowStatDtos = new ArrayList<>();

            try {
                List<CashFlowItem> items = cashFlowItemsDao.findAll(user);

                for (CashFlowItem item : items) {

                    List<CashFlowPlanItem> planItems = cashFlowDao.findAll(user, item);


                    cashFlowStatDtos.add(new CashFlowStatDto(item, countSpentCash(planItems)));
                }

            } catch (ConnectionPoolException | DBException e) {
                throw new ServiceException(e);
            }

            return cashFlowStatDtos;
        }
        throw new ServiceValidationException(user.toString());

    }


    private double countSpentCash(List<CashFlowPlanItem> planItems) {
        double answer = 0;
        for (CashFlowPlanItem planItem : planItems) {
            answer += planItem.getCost();
        }
        return answer;
    }


}

package by.epam.java_training.mihail_poliansky.final_project.dao.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.*;

public class DaoFactory {

    private static CashFlowDao cashFlowDao = new CashFlowDaoImpl();
    private static CashFlowItemsDao cashFlowItemsDao = new CashFlowItemsDaoImpl();
    private static TimeManagerDao timeManagerDao = new TimeManagerDaoImpl();
    private static TimeManagerItemsDao timeManagerItemsDao = new TimeManagerItemsDaoImpl();
    private static UserPrivatesDao userPrivatesDao = new UsersPrivatesDaoImpl();
    private static UsersDao usersDao = new UsersDaoImpl();
    private static ActivitiesDao activitiesDao = new ActivitiesDaoImpl();

    private DaoFactory() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static ActivitiesDao getActivitiesDao() {
        return activitiesDao;
    }

    public static CashFlowDao getCashFlowDao() {
        return cashFlowDao;
    }

    public static CashFlowItemsDao getCashFlowItemsDao() {
        return cashFlowItemsDao;
    }

    public static TimeManagerDao getTimeManagerDao() {
        return timeManagerDao;
    }

    public static TimeManagerItemsDao getTimeManagerItemsDao() {
        return timeManagerItemsDao;
    }

    public static UserPrivatesDao getUserPrivatesDao() {
        return userPrivatesDao;
    }

    public static UsersDao getUsersDao() {
        return usersDao;
    }
}

package by.epam.java_training.mihail_poliansky.final_project.service.impl;

import by.epam.java_training.mihail_poliansky.final_project.service.AuthService;
import by.epam.java_training.mihail_poliansky.final_project.service.CashFlowService;
import by.epam.java_training.mihail_poliansky.final_project.service.StatisticsService;
import by.epam.java_training.mihail_poliansky.final_project.service.TimeManagerService;

public class ServiceFactory {

    private static AuthService authService = new AuthServiceImpl();
    private static CashFlowService cashFlowService = new CashFlowServiceImpl();
    private static StatisticsService statisticsService = new StatisticsServiceImpl();
    private static TimeManagerService timeManagerService = new TimeManagerServiceImpl();

    private ServiceFactory() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static AuthService getAuthService() {
        return authService;
    }

    public static CashFlowService getCashFlowService() {
        return cashFlowService;
    }

    public static StatisticsService getStatisticsService() {
        return statisticsService;
    }

    public static TimeManagerService getTimeManagerService() {
        return timeManagerService;
    }
}

package com.example.mikle.daymanager.presenter.impl;

public class PresenterFactory {
    private static LoginPresenter loginPresenter = new LoginPresenter();
    private static RegistrationPresenter registrationPresenter = new RegistrationPresenter();
    private static CashFlowPresenter cashFlowPresenter = new CashFlowPresenter();
    private static DayPlanPresenter dayPlanPresenter = new DayPlanPresenter();
    private static StatisticsPresenter statisticsPresenter = new StatisticsPresenter();

    private PresenterFactory() {
    }

    public static LoginPresenter getLoginPresenter() {
        return loginPresenter;
    }

    public static RegistrationPresenter getRegistrationPresenter() {
        return registrationPresenter;
    }

    public static CashFlowPresenter getCashFlowPresenter() {
        return cashFlowPresenter;
    }

    public static DayPlanPresenter getDayPlanPresenter() {
        return dayPlanPresenter;
    }

    public static StatisticsPresenter getStatisticsPresenter() {
        return statisticsPresenter;
    }
}

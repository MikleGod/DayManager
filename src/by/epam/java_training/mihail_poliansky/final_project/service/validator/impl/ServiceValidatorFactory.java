package by.epam.java_training.mihail_poliansky.final_project.service.validator.impl;

import by.epam.java_training.mihail_poliansky.final_project.service.validator.AuthValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.CashFlowValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.StatisticsServiceValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.TimeManagerValidator;

public class ServiceValidatorFactory {

    //TODO implementation of validators

    private static AuthValidator authValidator = new AuthValidatorImpl();
    private static CashFlowValidator cashFlowValidator = new CashFlowValidatorImpl();
    private static TimeManagerValidator timeManagerValidator = new TimeManagerValidatorImpl();
    private static StatisticsServiceValidator statisticsServiceValidator = new StatisticsValidatorImpl();

    public static StatisticsServiceValidator getStatisticsServiceValidator() {
        return statisticsServiceValidator;
    }

    public static AuthValidator getAuthValidator() {
        return authValidator;
    }

    public static CashFlowValidator getCashFlowValidator() {
        return cashFlowValidator;
    }

    public static TimeManagerValidator getTimeManagerValidator() {
        return timeManagerValidator;
    }
}

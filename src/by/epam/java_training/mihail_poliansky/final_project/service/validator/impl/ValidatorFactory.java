package by.epam.java_training.mihail_poliansky.final_project.service.validator.impl;

import by.epam.java_training.mihail_poliansky.final_project.service.validator.AuthValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.CashFlowValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.StatisticsServiceValidator;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.TimeManagerValidator;

public class ValidatorFactory {

    //TODO implementation of validators

    private static AuthValidator authValidator;
    private static CashFlowValidator cashFlowValidator;
    private static TimeManagerValidator timeManagerValidator;
    private static StatisticsServiceValidator statisticsServiceValidator;

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

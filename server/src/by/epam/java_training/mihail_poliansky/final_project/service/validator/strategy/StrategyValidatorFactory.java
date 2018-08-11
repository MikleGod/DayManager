package by.epam.java_training.mihail_poliansky.final_project.service.validator.strategy;

import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;

public class StrategyValidatorFactory {
    private static Validator dateValidator = new DateValidator();
    private static Validator cashFlowPlanItemValidator = new CashFlowPlanItemValidator();
    private static Validator userPrivatesValidator = new UserPrivatesValidator();
    private static Validator userValidator = new UserValidator();
    private static Validator cashFlowItemValidator = new CashFlowItemValidator();
    private static Validator timeManagerItemValidator = new TimeManagerItemValidator();
    private static Validator timeManagerPlanItemValidator = new TimeManagerPlanItemValidator();

    public static Validator getDateValidator() {
        return dateValidator;
    }

    public static Validator getCashFlowPlanItemValidator() {
        return cashFlowPlanItemValidator;
    }

    public static Validator getUserPrivatesValidator() {
        return userPrivatesValidator;
    }

    public static Validator getUserValidator() {
        return userValidator;
    }

    public static Validator getCashFlowItemValidator() {
        return cashFlowItemValidator;
    }

    public static Validator getTimeManagerItemValidator() {
        return timeManagerItemValidator;
    }

    public static Validator getTimeManagerPlanItemValidator() {
        return timeManagerPlanItemValidator;
    }
}

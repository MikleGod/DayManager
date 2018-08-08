package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;

public enum JspCommandEnum {
    LOGIN(new JspLoginCommand()),
    CALENDAR(new JspCalendarCommand()),
    LOGOUT(new JspLogoutCommand()),
    STATISTICS(new JspStatisticsCommand()),
    PRIVATE(new JspPrivateCabinetCommand()),
    CHANGE_LANGUAGE(new JspChangeLanguageCommand());

    private ActionCommand command;

    JspCommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}

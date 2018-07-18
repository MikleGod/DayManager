package by.epam.java_training.mihail_poliansky.final_project.command.jsp_impl;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;

public enum JspCommandEnum {
    LOGIN(new JspLoginCommand());

    private ActionCommand command;

    JspCommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}

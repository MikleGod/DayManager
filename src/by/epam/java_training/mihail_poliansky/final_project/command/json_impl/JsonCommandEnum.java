package by.epam.java_training.mihail_poliansky.final_project.command.json_impl;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;

public enum JsonCommandEnum {
    REGISTRATION(new JsonRegistrationCommand()),
    LOGIN(new JsonRegistrationCommand());

    private ActionCommand command;

    JsonCommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}

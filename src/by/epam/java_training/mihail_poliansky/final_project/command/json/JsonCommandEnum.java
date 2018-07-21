package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;

public enum JsonCommandEnum {
    REGISTRATION(new JsonRegistrationCommand()),
    LOGIN(new JsonRegistrationCommand()),
    CHECK_MAIL(new CheckMailCommand());

    private ActionCommand command;

    JsonCommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}

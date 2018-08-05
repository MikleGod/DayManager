package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;

public enum JsonCommandEnum {
    REGISTRATION(new JsonRegistrationCommand()),
    LOGIN(new JsonRegistrationCommand()),
    CHECK_MAIL(new CheckMailCommand()),
    ADD_TMPI(new AddTmpiCommand()),
    ADD_TMI(new AddTmiCommand()),
    DELETE_TMI(new DeleteTmiCommand()),
    DELETE_CFI(new DeleteCfiCommand()),
    ADD_CFI(new AddCfiCommand()),
    ADD_CFPI(new AddCfpiCommand()),
    CHANGE_PRIVATES(new ChangePrivatesCommand());

    private ActionCommand command;

    JsonCommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommand() {
        return command;
    }
}

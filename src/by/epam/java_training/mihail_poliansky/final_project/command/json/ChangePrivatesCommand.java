package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangePrivatesCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger(ChangePrivatesCommand.class);


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("execute");

        //TODO change password service method
    }
}

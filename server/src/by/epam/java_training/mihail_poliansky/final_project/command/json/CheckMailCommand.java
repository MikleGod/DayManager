package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckMailCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger(CheckMailCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            boolean isExists = ServiceFactory.getAuthService().isUserExists(req.getParameter(ParametersNames.MAIL));
            logger.info("isUserExists = " + isExists);
            resp.getWriter().write(
                    "{\"isExists\":"
                            + "\"" + isExists + "\""
                            + "}"
            );
        } catch (ServiceException | IOException e) {
            logger.info(e.toString());
            //TODO forward to error page
            e.printStackTrace();
        }
    }
}

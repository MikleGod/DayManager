package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.DayManagerException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.service.validator.ServiceValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTmiCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger(DeleteTmiCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("execute");
        try {

            ServiceFactory.getTimeManagerService().deleteTMItem(
                    (User)req.getSession().getAttribute("user"),
                    new TimeManagerItem("", Integer.parseInt(req.getParameter("tmi_id")))
            );

        } catch (ServiceException e) {
            repayException(resp, e);
        }
    }

    private void repayException(HttpServletResponse resp, DayManagerException e) {
        try {
            resp.getWriter().write(JsonCreator.createJsonMessage(e.getMessage()));
        } catch (IOException e1) {
            logger.info(e1.toString());
        }
    }
}

package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.DateParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddTmpiCommand implements ActionCommand {
    static Logger logger = LogManager.getLogger(AddTmpiCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("AddTmpiCommand in Action");

        try {
            TimeManagerPlanItem planItem = new TimeManagerPlanItem(
                    new TimeManagerItem("", Integer.parseInt(req.getParameter("tmi_id"))),
                    req.getParameter("timeBegin"),
                    req.getParameter("timeEnd"),
                    (User) req.getSession().getAttribute("user"),
                    DateParser.parseDate(req.getParameter("date")),
                    0
            );
            ServiceFactory.getTimeManagerService().addPlan(planItem);
        } catch (ServiceException e) {
            repayException(resp, e);
        }
    }

    private void repayException(HttpServletResponse resp, ServiceException e) {
        try {
            resp.getWriter().write(JsonCreator.createJsonMessage(e.getMessage()));
        } catch (IOException e1) {
            logger.info(e1.toString());
        }
    }
}

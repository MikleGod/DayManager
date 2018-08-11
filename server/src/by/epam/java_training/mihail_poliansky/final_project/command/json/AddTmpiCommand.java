package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames;
import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.DateParser;
import by.epam.java_training.mihail_poliansky.final_project.util.ExceptionStringCreator;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.*;
import static by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames.DATE;

public class AddTmpiCommand implements ActionCommand {
    static Logger logger = LogManager.getLogger(AddTmpiCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("AddTmpiCommand in Action");


        try {

            ResourceManager resourceManager = (ResourceManager) req.getSession().getAttribute(RESOURCE_MANAGER);
            boolean isOpened = ((List<ActivityEnum>) req.getSession().getAttribute(ACTIVITIES)).contains(ActivityEnum.ADD_TMPI);
            if (isOpened) {


                TimeManagerPlanItem planItem = new TimeManagerPlanItem(
                        new TimeManagerItem("", Integer.parseInt(req.getParameter(ParametersNames.TMI_ID))),
                        req.getParameter(ParametersNames.TIME_BEGIN),
                        req.getParameter(ParametersNames.TIME_END),
                        (User) req.getSession().getAttribute(USER),
                        DateParser.parseDate(req.getParameter(DATE)),
                        0
                );
                ServiceFactory.getTimeManagerService().addPlan(planItem);
            } else {
                resp.getWriter().write(JsonCreator.createJsonMessage(resourceManager.getErrorString(ACTIVITIES_ERROR)));
            }
        } catch (ServiceException|IOException e) {
            logger.info(ExceptionStringCreator.createString(e));
            repayException(resp, e);
        }
    }

    private void repayException(HttpServletResponse resp, Exception e) {
        try {
            resp.getWriter().write(JsonCreator.createJsonMessage(e.getMessage()));
        } catch (IOException e1) {
            logger.info(e1.toString());
        }
    }
}

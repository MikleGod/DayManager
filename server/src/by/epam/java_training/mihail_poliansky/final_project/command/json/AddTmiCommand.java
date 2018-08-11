package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames;
import by.epam.java_training.mihail_poliansky.final_project.entity.ActivityEnum;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.*;
import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.RESOURCE_MANAGER;
import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.USER;

public class AddTmiCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger(AddTmiCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("execute");
        try {
            ResourceManager resourceManager = (ResourceManager) req.getSession().getAttribute(RESOURCE_MANAGER);
            if (((List<ActivityEnum>) req.getSession().getAttribute(ACTIVITIES)).contains(ActivityEnum.ADD_TMI)) {


                ServiceFactory.getTimeManagerService().addTMItem((User) req.getSession().getAttribute(USER), new TimeManagerItem(req.getParameter(ParametersNames.TMI_NAME), 0));

            } else {
                //resp.getWriter().write(JsonCreator.createJsonMessage(resourceManager.getErrorString("activitiesError")));
            }
        } catch (ServiceException/*|IOException*/ e) {
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

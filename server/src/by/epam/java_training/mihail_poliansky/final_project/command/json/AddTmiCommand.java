package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames;
import by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames;
import by.epam.java_training.mihail_poliansky.final_project.entity.ActivityEnum;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.*;
import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.RESOURCE_MANAGER;
import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.USER;

public class
AddTmiCommand implements ActionCommand {
    private static final String BAD_ACT_MESSAGE = "U cant do it!";
    private static final String ERROR_MESSAGE = "Sorry, smth wrong!";

    private static Logger logger = LogManager.getLogger(AddTmiCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("execute 1");
        try {
            logger.info("execute 2");
            //ResourceManager resourceManager = (ResourceManager) req.getSession().getAttribute(RESOURCE_MANAGER);
            //logger.info("execute 3" + resourceManager);
            List<ActivityEnum> activityEnumList = (List<ActivityEnum>) req.getSession().getAttribute(ACTIVITIES);
            logger.info("execute 4" + activityEnumList);
            if (activityEnumList != null && activityEnumList.contains(ActivityEnum.ADD_TMI)) {
                logger.info("execute 5");
                TimeManagerItem item = ServiceFactory.getTimeManagerService().addTMItem((User) req.getSession().getAttribute(USER), new TimeManagerItem(req.getParameter(ParametersNames.TMI_NAME), 0));
                logger.info("execute6");
                resp.getWriter().write(JSON.toJSONString(item));
                logger.info("execute 7");
            } else {
                resp.getWriter().write(JSON.toJSONString(new Message("Error!")));
            }
        } catch (ServiceException|IOException e) {
            repayException(resp, e);
        }
    }

    private void repayException(HttpServletResponse resp, Exception e) {
        try {
            resp.getWriter().write(JSON.toJSONString(new Message(ERROR_MESSAGE)));
        } catch (IOException e1) {
            logger.info(e1.toString());
        }
    }
}

package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.ActivityEnum;
import by.epam.java_training.mihail_poliansky.final_project.entity.Role;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.ExceptionStringCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames.*;

public class DeleteActivityCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger(DeleteActivityCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(Role.USER, "", Integer.parseInt(req.getParameter(USR_ID)));
        ActivityEnum activity = ActivityEnum.valueOf(req.getParameter(ACT_ID));
        try {
            ServiceFactory.getAdminService().deleteActivity(user, activity);
        } catch (ServiceException e) {
            logger.info(ExceptionStringCreator.createString(e));
        }
    }
}

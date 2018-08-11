package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames;
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

public class AddActivityCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger(AddActivityCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(Role.USER, "", Integer.parseInt(req.getParameter(ParametersNames.USR_ID)));
        ActivityEnum activity = ActivityEnum.valueOf(req.getParameter(ParametersNames.ACT_ID));
        try {
            ServiceFactory.getAdminService().addActivity(user, activity);
        } catch (ServiceException e) {
            logger.info(ExceptionStringCreator.createString(e));
        }
    }
}

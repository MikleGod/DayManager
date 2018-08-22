package by.epam.java_training.mihail_poliansky.final_project.command;

import by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames;
import by.epam.java_training.mihail_poliansky.final_project.command.jsp.PageOpener;
import by.epam.java_training.mihail_poliansky.final_project.entity.ActivityEnum;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchUserException;
import by.epam.java_training.mihail_poliansky.final_project.factory.EntityFactory;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.ExceptionStringCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.ACTIVITIES;
import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.USER;
import static by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames.MAIL;

public abstract class LoginCommand extends PageOpener {
    Logger logger = LogManager.getLogger(LoginCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            logger.info("EXECUTE");
            User user = ServiceFactory.getAuthService()
                    .login(EntityFactory.createUserPrivates(
                            req.getParameter(MAIL), req.getParameter(ParametersNames.PASSWORD).hashCode())
                    );
            logger.info(user.toString());
            req.getSession(true).setAttribute(USER, user);
            List<ActivityEnum> activities = ServiceFactory.getAuthService().findActivities(user);
            logger.info(activities.toString());
            req.getSession(true).setAttribute(ACTIVITIES, activities);
            doMandatory(user, req, resp);
        } catch (NoSuchUserException e) {
            logger.info(ExceptionStringCreator.createString(e));
            doAlternative(e, req, resp);
        } catch (ServiceException e) {
            logger.info(ExceptionStringCreator.createString(e));
            doAlternative(e, req, resp);
        }
    }

    protected abstract void doAlternative(NoSuchUserException e, HttpServletRequest req, HttpServletResponse resp);

    protected abstract void doAlternative(ServiceException e, HttpServletRequest req, HttpServletResponse resp);

    protected abstract void doMandatory(User user, HttpServletRequest req, HttpServletResponse resp);
}

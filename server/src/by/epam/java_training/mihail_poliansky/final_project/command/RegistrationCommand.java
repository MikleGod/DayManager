package by.epam.java_training.mihail_poliansky.final_project.command;

import by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.entity.UserPrivates;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.UserAlreadyExistsException;
import by.epam.java_training.mihail_poliansky.final_project.factory.EntityFactory;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames.MAIL;
import static by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames.PASSWORD;

public abstract class RegistrationCommand implements ActionCommand{
    private Logger logger = LogManager.getLogger(RegistrationCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            logger.info("registration");
            User user = EntityFactory.createUser(req.getParameter(ParametersNames.ROLE), req.getParameter(ParametersNames.NICKNAME));
            UserPrivates privates = EntityFactory.createUserPrivates(
                    req.getParameter(MAIL), req.getParameter(PASSWORD).hashCode());
            ServiceFactory.getAuthService().registration(user, privates);
            logger.info("mandatory");
            doMandatory(req, resp, user);
        } catch (UserAlreadyExistsException e) {
            logger.info(e.toString());
            doAlternative(req, resp, e);
        } catch (ServiceException e) {
            logger.info(e.toString());
            doAlternative(req, resp, e);
        }
    }

    protected abstract void doAlternative(HttpServletRequest req, HttpServletResponse resp, UserAlreadyExistsException e);

    protected abstract void doAlternative(HttpServletRequest req, HttpServletResponse resp, ServiceException e);

    protected abstract void doMandatory(HttpServletRequest req, HttpServletResponse resp, User user);
}

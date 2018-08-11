package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames;
import by.epam.java_training.mihail_poliansky.final_project.constant.JspPages;
import by.epam.java_training.mihail_poliansky.final_project.command.LoginCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.Role;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchUserException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.LANG;

public class JspLoginCommand extends LoginCommand {

    private static Logger logger = LogManager.getLogger(JspLoginCommand.class);

    @Override
    protected void doAlternative(NoSuchUserException e, HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute(AttributeNames.EXCEPTION_MESSAGE, "No Such User");
        dispatch(JspPages.REGISTRATION_LOGIN_JSP, req, resp);
    }


    @Override
    protected void doAlternative(ServiceException e, HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.getWriter().write(e.toString());
        } catch (IOException e1) {
            e1.printStackTrace();
            logger.info(e1.toString());
        }
    }

    @Override
    protected void doMandatory(User user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession(true).setAttribute(LANG, new ResourceManager());
        logger.info(user.getRole());
        switch (user.getRole()) {
            case USER:
                logger.info(Role.USER);
                JspCommandEnum.CALENDAR.getCommand().execute(req, resp);
                break;
            case ADMIN:
                logger.info(Role.ADMIN);
                JspCommandEnum.ADMIN.getCommand().execute(req, resp);
                break;
        }
    }

}

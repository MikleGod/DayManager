package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.java_training.mihail_poliansky.final_project.constant.JspPages.REGISTRATION_LOGIN_JSP;

public class JspLogoutCommand extends PageOpener {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        LogManager.getLogger(JspLogoutCommand.class).info("forwarded");
        dispatch(REGISTRATION_LOGIN_JSP, req, resp);
    }
}

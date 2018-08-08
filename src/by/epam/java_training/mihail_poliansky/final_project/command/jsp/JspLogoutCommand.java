package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JspLogoutCommand extends PageOpener {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        LogManager.getLogger(JspLogoutCommand.class).info("forwarded");
        dispatch("/registration-login.jsp", req, resp);
    }
}

package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.command.LoginCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchUserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JspLoginCommand  extends LoginCommand {

    private static Logger logger = LogManager.getLogger(JspLoginCommand.class);

    @Override
    protected void doAlternative(NoSuchUserException e, HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doAlternative(ConnectionPoolException e, HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doAlternative(DBException e, HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.getWriter().write(e.toString());
        } catch (IOException e1) {
            e1.printStackTrace();
            logger.info(e1.toString());
        }
    }

    @Override
    protected void doMandatory(User user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession(true).setAttribute("user", user);
        JspCommandEnum.CALENDAR.getCommand().execute(req, resp);
    }

}

package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.util.ExceptionStringCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.java_training.mihail_poliansky.final_project.constant.JspPages.REGISTRATION_LOGIN_JSP;

public abstract class PageOpener implements ActionCommand {

    static Logger logger = LogManager.getLogger(PageOpener.class);

    protected void dispatch(String page, HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (!resp.isCommitted()) {
                req.getServletContext().getRequestDispatcher(page).forward(req, resp);
            }
        } catch (ServletException e) {
            openErrorPage(req, resp, e);
        } catch (IOException e) {
            logger.info(ExceptionStringCreator.createString(e));
        }
    }

    public void openErrorPage(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        logger.info(ExceptionStringCreator.createString(e));
        dispatch(REGISTRATION_LOGIN_JSP, req, resp);
    }
}

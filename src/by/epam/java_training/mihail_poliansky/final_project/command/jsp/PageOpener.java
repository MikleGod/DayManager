package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class PageOpener implements ActionCommand {
    protected void dispatch(String page, HttpServletRequest req, HttpServletResponse resp){
        try {
            req.getRequestDispatcher(req.getServletContext().getContextPath()+page).forward(req, resp);
        } catch (ServletException | IOException e) {
            openErrorPage(req, resp, e);
        }
    }

    public void openErrorPage(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        //TODO open Error page
    }
}

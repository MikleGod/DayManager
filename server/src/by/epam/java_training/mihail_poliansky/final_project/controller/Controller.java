package by.epam.java_training.mihail_poliansky.final_project.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.java_training.mihail_poliansky.final_project.constant.JspPages.REGISTRATION_LOGIN_JSP;

public class Controller extends HttpServlet {
    private final static Logger logger = LogManager.getLogger(Controller.class);


    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        logger.info("Get");
        try {
            req.getRequestDispatcher(REGISTRATION_LOGIN_JSP).forward(req, resp);
        } catch (ServletException | IOException e) {
            logger.info(e.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        logger.info("Post");
        CommandManager.chooseCommand(req).execute(req, resp);
    }
}

package by.epam.java_training.mihail_poliansky.final_project.controller;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.command.json.JsonCommandEnum;
import by.epam.java_training.mihail_poliansky.final_project.command.jsp.JspCommandEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class CommandManager {
    private static Logger logger = LogManager.getLogger(CommandManager.class);

    public static ActionCommand chooseCommand(HttpServletRequest req) {
        log(req);
        if (req.getParameter("json") != null && !req.getParameter("json").isEmpty()) {
            return chooseJsonCommand(req);
        } else {
            return chooseJspCommand(req);
        }
    }

    private static void log(HttpServletRequest req) {
        String log = "\n";
        Enumeration<String> keys = req.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();

            log += key + ": " + req.getParameter(key) + "\n";
        }
        logger.info(log);
    }

    private static ActionCommand chooseJspCommand(HttpServletRequest req) {
        ActionCommand action = JspCommandEnum.valueOf(req.getParameter("action").toUpperCase()).getCommand();
        logger.info("\naction: "+ action.toString());
        return action;
    }

    private static ActionCommand chooseJsonCommand(HttpServletRequest req) {
        JsonCommandEnum action = JsonCommandEnum.valueOf(req.getParameter("action").toUpperCase());
        logger.info("\naction: "+ action.toString());
        return action.getCommand();
    }
}

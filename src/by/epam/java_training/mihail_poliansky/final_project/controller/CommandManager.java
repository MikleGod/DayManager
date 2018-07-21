package by.epam.java_training.mihail_poliansky.final_project.controller;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.command.json.JsonCommandEnum;
import by.epam.java_training.mihail_poliansky.final_project.command.jsp.JspCommandEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandManager {
    private static Logger logger = LogManager.getLogger(CommandManager.class);

    public static ActionCommand chooseCommand(HttpServletRequest req){
        logger.info(req);
        if (req.getParameter("json") != null && !req.getParameter("json").isEmpty()){
            return chooseJsonCommand(req);
        } else {
            return chooseJspCommand(req);
        }
    }

    private static ActionCommand chooseJspCommand(HttpServletRequest req) {
        return JspCommandEnum.valueOf(req.getParameter("action").toUpperCase()).getCommand();
    }

    private static ActionCommand chooseJsonCommand(HttpServletRequest req){
        return JsonCommandEnum.valueOf(req.getParameter("action").toUpperCase()).getCommand();
    }
}

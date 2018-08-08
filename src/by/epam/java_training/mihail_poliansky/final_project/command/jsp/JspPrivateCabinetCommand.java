package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;

public class JspPrivateCabinetCommand extends PageOpener {

    static Logger logger = LogManager.getLogger(JspPrivateCabinetCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("execute");

        User user = (User) req.getSession().getAttribute("user");

        try {

            ((ResourceManager)req.getSession().getAttribute("lang")).addLocalizationAttributes(req);

            List<TimeManagerItem> timeManagerItems = ServiceFactory.getTimeManagerService().getTMItems(user);
            List<CashFlowItem> cashFlowItems = ServiceFactory.getCashFlowService().getCFItems(user);

            req.setAttribute("timeManagerItems", timeManagerItems);
            req.setAttribute("cashFlowItems", cashFlowItems);

            dispatch("/jsp/private-room.jsp", req, resp);
        } catch (ServiceException e) {
            logger.info(e.toString());
            openErrorPage(req, resp, e);
        }

    }


}

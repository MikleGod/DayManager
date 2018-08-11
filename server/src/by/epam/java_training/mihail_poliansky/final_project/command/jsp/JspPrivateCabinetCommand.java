package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.constant.JspPages;
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
import java.util.List;

import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.*;

public class JspPrivateCabinetCommand extends PageOpener {

    static Logger logger = LogManager.getLogger(JspPrivateCabinetCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("execute");

        User user = (User) req.getSession().getAttribute(USER);

        try {

            ((ResourceManager)req.getSession().getAttribute(LANG)).addLocalizationAttributes(req);

            List<TimeManagerItem> timeManagerItems = ServiceFactory.getTimeManagerService().getTMItems(user);
            List<CashFlowItem> cashFlowItems = ServiceFactory.getCashFlowService().getCFItems(user);

            req.setAttribute(TIME_MANAGER_ITEMS, timeManagerItems);
            req.setAttribute(CASH_FLOW_ITEMS, cashFlowItems);

            dispatch(JspPages.JSP_PRIVATE_ROOM_JSP, req, resp);
        } catch (ServiceException e) {
            logger.info(e.toString());
            openErrorPage(req, resp, e);
        }

    }


}

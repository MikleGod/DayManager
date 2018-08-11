package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames;
import by.epam.java_training.mihail_poliansky.final_project.constant.JspPages;
import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import by.epam.java_training.mihail_poliansky.final_project.service.CashFlowService;
import by.epam.java_training.mihail_poliansky.final_project.service.TimeManagerService;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.DayManagerException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.DateParser;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.LANG;
import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.USER;
import static by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames.DATE;

public class JspCalendarCommand extends PageOpener {

    private static Logger logger = LogManager.getLogger(JspCalendarCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        String dateString = req.getParameter(DATE);
        Date date = DateParser.parseDate(dateString);

        TimeManagerService timeManagerService = ServiceFactory.getTimeManagerService();
        CashFlowService cashFlowService = ServiceFactory.getCashFlowService();

        try {

            ((ResourceManager) req.getSession().getAttribute(LANG)).addLocalizationAttributes(req);

            User us = (User) req.getSession().getAttribute(USER);
            List<TimeManagerItem> timeManagerItems = timeManagerService.getTMItems(us);
            List<CashFlowItem> cashFlowItems = cashFlowService.getCFItems(us);

            List<TimeManagerPlanItem> planItemsList = timeManagerService.getPlan(us, date);
            List<CashFlowPlanItem> planItemsCash = cashFlowService.getCF(us, date);

            req.setAttribute(AttributeNames.TIME_MANAGER_ITEMS, timeManagerItems);
            req.setAttribute(AttributeNames.TIME_MANAGER_PLAN_ITEMS, planItemsList);
            req.setAttribute(AttributeNames.CASH_FLOW_ITEMS, cashFlowItems);
            req.setAttribute(AttributeNames.CASH_FLOW_PLAN_ITEMS, planItemsCash);
            req.setAttribute(DATE, date.toString());

        } catch (DayManagerException e) {
            logger.info(e.toString());
            repayException(e, req, resp);
        }

        dispatch(JspPages.JSP_CALENDAR_JSP, req, resp);
    }

    private void repayException(DayManagerException e, HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute(AttributeNames.VALIDATION, e.getMessage());
    }
}

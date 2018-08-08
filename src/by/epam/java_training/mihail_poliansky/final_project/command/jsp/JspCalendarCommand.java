package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import by.epam.java_training.mihail_poliansky.final_project.service.CashFlowService;
import by.epam.java_training.mihail_poliansky.final_project.service.TimeManagerService;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.DayManagerException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.DateParser;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class JspCalendarCommand extends PageOpener {

    private static Logger logger = LogManager.getLogger(JspCalendarCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        String dateString = req.getParameter("date");
        Date date;
        if (dateString != null && !dateString.isEmpty()) {
            date = DateParser.parseDate(dateString);
        } else {
            date = Date.valueOf(LocalDate.now());
        }

        TimeManagerService timeManagerService = ServiceFactory.getTimeManagerService();
        CashFlowService cashFlowService = ServiceFactory.getCashFlowService();

        try {

            ((ResourceManager)req.getSession().getAttribute("lang")).addLocalizationAttributes(req);

            List<TimeManagerItem> timeManagerItems = timeManagerService.getTMItems((User) req.getSession().getAttribute("user"));
            List<CashFlowItem> cashFlowItems = cashFlowService.getCFItems((User) req.getSession().getAttribute("user"));

            List<TimeManagerPlanItem> planItemsList = timeManagerService.getPlan((User) req.getSession().getAttribute("user"), date);
            List<CashFlowPlanItem> planItemsCash = cashFlowService.getCF((User) req.getSession().getAttribute("user"), date);

            req.setAttribute("timeManagerItems", timeManagerItems);
            req.setAttribute("timeManagerPlanItems", planItemsList);
            req.setAttribute("cashFlowItems", cashFlowItems);
            req.setAttribute("cashFlowPlanItems", planItemsCash);
            req.setAttribute("date", date.toString());

        } catch (DayManagerException e) {
            logger.info(e.toString());
            repayException(e, req, resp);
        }

        dispatch("/jsp/calendar.jsp", req, resp);
    }

    private void repayException(DayManagerException e, HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("validation", e.getMessage());
    }
}

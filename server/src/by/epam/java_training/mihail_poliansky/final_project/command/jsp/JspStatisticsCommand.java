package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames;
import by.epam.java_training.mihail_poliansky.final_project.constant.JspPages;
import by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames;
import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import by.epam.java_training.mihail_poliansky.final_project.service.StatisticsService;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.DateParser;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.*;

public class JspStatisticsCommand extends PageOpener {

    static Logger logger = LogManager.getLogger(JspStatisticsCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("Execute");

        ResourceManager resourceManager = (ResourceManager) req.getSession().getAttribute(LANG);
        resourceManager.addLocalizationAttributes(req);

        Date from = DateParser.parseDate(req.getParameter(ParametersNames.FROM));
        Date to = DateParser.parseDate(req.getParameter(ParametersNames.TO));

        try {
            StatisticsService statisticsService = ServiceFactory.getStatisticsService();
            User user = (User) req.getSession().getAttribute(USER);

            List<TimeManagerStatDto> timeManageStatDto;
            List<CashFlowStatDto> cashFlowStatDto;

            if (((List<ActivityEnum>)req.getSession().getAttribute(ACTIVITIES)).contains(ActivityEnum.WATCH_STAT)) {

                if (from.equals(to)) {
                    cashFlowStatDto = statisticsService.countSpentMoney(user);
                    timeManageStatDto = statisticsService.countSpentTime(user);
                } else {
                    cashFlowStatDto = statisticsService.countSpentMoney(user, from, to);
                    timeManageStatDto = statisticsService.countSpentTime(user, from, to);
                }

                req.setAttribute(AttributeNames.TIME_MANAGE_STAT_DTO, timeManageStatDto);
                req.setAttribute(AttributeNames.CASH_FLOW_STAT_DTO, cashFlowStatDto);
            } else {
                req.setAttribute(AttributeNames.ERROR_MESSAGE, resourceManager.getErrorString(ERROR_MESSAGE));
            }
        } catch (ServiceException e) {
            logger.info(Arrays.toString(e.getStackTrace()));
            //TODO repay Exception
        }

        dispatch(JspPages.JSP_STATISTICS_JSP, req, resp);
    }
}

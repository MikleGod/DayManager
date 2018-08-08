package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import by.epam.java_training.mihail_poliansky.final_project.service.StatisticsService;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class JspStatisticsCommand extends PageOpener {

    static Logger logger = LogManager.getLogger(JspStatisticsCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("Execute");

        ((ResourceManager)req.getSession().getAttribute("lang")).addLocalizationAttributes(req);

        try {
            StatisticsService statisticsService = ServiceFactory.getStatisticsService();
            User user = (User) req.getSession().getAttribute("user");
            double allMoney = statisticsService.countAllSpentMoney(user);
            String allTime = statisticsService.countAllSpentTime(user);

            ArrayList<TimeManagerStatDto> timeManageStatDto = new ArrayList<>();
            ArrayList<CashFlowStatDto> cashFlowStatDto = new ArrayList<>();
            timeManageStatDto.add(new TimeManagerStatDto(new TimeManagerItem("all", 0), allTime));
            cashFlowStatDto.add(new CashFlowStatDto(new CashFlowItem("all", 0), allMoney));
            req.setAttribute("timeManageStatDto", timeManageStatDto);
            req.setAttribute("cashFlowStatDto", cashFlowStatDto);
        } catch (ServiceException e) {
            //TODO repay Exception
        }

        dispatch("/jsp/statistics.jsp", req, resp);
    }
}

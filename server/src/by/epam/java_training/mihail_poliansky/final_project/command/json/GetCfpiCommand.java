package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.CashFlowService;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.DateParser;
import by.epam.java_training.mihail_poliansky.final_project.util.ExceptionStringCreator;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.USER;
import static by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames.DATE;

public class GetCfpiCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger(GetCfpiCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String dateString = req.getParameter(DATE);
        Date date = DateParser.parseDate(dateString);

        CashFlowService cashFlowService = ServiceFactory.getCashFlowService();
        try {
            User us = (User) req.getSession().getAttribute(USER);
            List<CashFlowPlanItem> planItemsList = cashFlowService.getCF(us, date);
            Map<String, Object[]> map = new HashMap<>();
            map.put("cfpItems", planItemsList.toArray());
            resp.getWriter().write(JSON.toJSONString(map));
        } catch (ServiceException | IOException e) {
            repayException(e, resp);
        }
    }

    private void repayException(Exception e, HttpServletResponse resp) {
        try {
            resp.getWriter().write(JSON.toJSONString(new Message(e.getMessage())));
        } catch (IOException e1) {
            logger.info(ExceptionStringCreator.createString(e));
        }
    }
}

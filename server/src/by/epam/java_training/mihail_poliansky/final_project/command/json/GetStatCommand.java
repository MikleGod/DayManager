package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowStatDto;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerStatDto;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.ExceptionStringCreator;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetStatCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger(GetStatCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<CashFlowStatDto> cashFlowStatDtos = ServiceFactory.getStatisticsService().countSpentMoney((User)req.getSession().getAttribute(AttributeNames.USER));
            List<TimeManagerStatDto> timeManagerStatDtos = ServiceFactory.getStatisticsService().countSpentTime((User)req.getSession().getAttribute(AttributeNames.USER));
            Map<String, Object[]> map = new HashMap<>();
            map.put("cashFlowStatDtos", cashFlowStatDtos.toArray());
            map.put("timeManagerStatDtos", timeManagerStatDtos.toArray());
            resp.getWriter().write(JSON.toJSONString(map));
        } catch (ServiceException|IOException e) {
            repayException(resp, e);
        }
    }

    private void repayException(HttpServletResponse resp, Exception e) {
        try {
            resp.getWriter().write(JSON.toJSONString(new Message(e.getMessage())));
        } catch (IOException e1) {
            logger.info(ExceptionStringCreator.createString(e));
        }
    }
}

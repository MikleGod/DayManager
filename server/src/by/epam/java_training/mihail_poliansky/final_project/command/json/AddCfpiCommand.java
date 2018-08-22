package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames;
import by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames;
import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.DateParser;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.*;

public class AddCfpiCommand implements ActionCommand {

    static Logger logger = LogManager.getLogger(AddCfpiCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("AddCfpiCommand in Action");
        try {
            ResourceManager resourceManager = (ResourceManager) req.getSession().getAttribute(RESOURCE_MANAGER);
            if (((List<ActivityEnum>) req.getSession().getAttribute(ACTIVITIES)).contains(ActivityEnum.ADD_CFPI)) {


                CashFlowPlanItem planItem = new CashFlowPlanItem(
                        0,
                        new CashFlowItem("", Integer.parseInt(req.getParameter(ParametersNames.CFI_ID))),
                        Double.parseDouble(req.getParameter(ParametersNames.COST)),
                        DateParser.parseDate(req.getParameter(ParametersNames.DATE)),
                        (User) req.getSession().getAttribute(USER)
                );
                planItem = ServiceFactory.getCashFlowService().addCF(planItem);
                Map<String, CashFlowPlanItem> map = new HashMap<>();
                map.put("cfpi", planItem);
                resp.getWriter().write(JSON.toJSONString(map));
            } else {
                resp.getWriter().write(JsonCreator.createJsonMessage(resourceManager.getErrorString(AttributeNames.ACTIVITIES_ERROR)));
            }
        } catch (ServiceException | IOException e) {
            repayException(resp, e);
        }
    }

    private void repayException(HttpServletResponse resp, Exception e) {
        try {
            resp.getWriter().write(JSON.toJSONString(new Message(e.getMessage())));
        } catch (IOException e1) {
            logger.info(e1.toString());
        }
    }
}

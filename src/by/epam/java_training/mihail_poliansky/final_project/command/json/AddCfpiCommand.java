package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.DateParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCfpiCommand implements ActionCommand {

    static Logger logger = LogManager.getLogger(AddCfpiCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("AddCfpiCommand in Action");
        try {
            CashFlowPlanItem planItem = new CashFlowPlanItem(
                    0,
                    new CashFlowItem("", Integer.parseInt(req.getParameter("cfi_id"))),
                    Double.parseDouble(req.getParameter("cost")),
                    DateParser.parseDate(req.getParameter("date")),
                    (User) req.getSession().getAttribute("user")
            );
            ServiceFactory.getCashFlowService().addCF(planItem);
        } catch (ServiceException e) {
            repayException(resp, e);
        }
    }

    private void repayException(HttpServletResponse resp, ServiceException e) {
        try {
            resp.getWriter().write(JsonCreator.createJsonMessage(e.getMessage()));
        } catch (IOException e1) {
            logger.info(e1.toString());
        }
    }
}

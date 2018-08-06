package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCfiCommand implements ActionCommand {

    private static Logger logger = LogManager.getLogger(DeleteCfiCommand.class);


    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("execute");

        try {

            ServiceFactory.getCashFlowService().deleteCFItem(
                    (User)req.getSession().getAttribute("user"),
                    new CashFlowItem("", Integer.parseInt(req.getParameter("cfi_id")))
            );

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

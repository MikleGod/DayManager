package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.ActionCommand;
import by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames;
import by.epam.java_training.mihail_poliansky.final_project.entity.ActivityEnum;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddCfiCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger(AddCfiCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("execute");

        ResourceManager resourceManager = (ResourceManager)req.getSession().getAttribute(AttributeNames.RESOURCE_MANAGER);

        try {
            if (((List<ActivityEnum>) req.getSession().getAttribute(AttributeNames.ACTIVITIES)).contains(ActivityEnum.ADD_CFI)) {

                ServiceFactory.getCashFlowService().addCFItem((User) req.getSession().getAttribute(AttributeNames.USER), new CashFlowItem(req.getParameter("cfi_name"), 0));
            } else {
                resp.getWriter().write(JsonCreator.createJsonMessage(resourceManager.getErrorString(AttributeNames.ACTIVITIES_ERROR)));
            }
        } catch (ServiceException|IOException e) {
            repayException(resp, e);
        }
    }

    private void repayException(HttpServletResponse resp, Exception e) {
        try {
            resp.getWriter().write(JsonCreator.createJsonMessage(e.getMessage()));
        } catch (IOException e1) {
            logger.info(e1.toString());
        }
    }
}

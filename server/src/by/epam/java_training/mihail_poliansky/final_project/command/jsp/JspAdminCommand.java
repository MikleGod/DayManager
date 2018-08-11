package by.epam.java_training.mihail_poliansky.final_project.command.jsp;


import by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames;
import by.epam.java_training.mihail_poliansky.final_project.constant.JspPages;
import by.epam.java_training.mihail_poliansky.final_project.entity.ActivityEnum;
import by.epam.java_training.mihail_poliansky.final_project.entity.UserActivitiesDto;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.ExceptionStringCreator;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class JspAdminCommand extends PageOpener {

    private static Logger logger = LogManager.getLogger(JspAdminCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        try {
            ((ResourceManager) req.getSession().getAttribute(AttributeNames.LANG)).addLocalizationAttributes(req);


            List<UserActivitiesDto> dtos = ServiceFactory.getAdminService().findAllUsersAndActs();

            List<ActivityEnum> allActivities = ServiceFactory.getAdminService().findAllActivities();

            req.setAttribute(AttributeNames.ALL_ACTIVITIES, allActivities);
            req.setAttribute(AttributeNames.DTOS, dtos);
        } catch (ServiceException e){
            logger.info(ExceptionStringCreator.createString(e));
        }


        dispatch(JspPages.JSP_ADMIN_PAGE_JSP, req, resp);
    }
}

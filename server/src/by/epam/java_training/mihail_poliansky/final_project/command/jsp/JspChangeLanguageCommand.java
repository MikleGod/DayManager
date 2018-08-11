package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.entity.LanguageEnum;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.LANG;
import static by.epam.java_training.mihail_poliansky.final_project.constant.AttributeNames.USER;

public class JspChangeLanguageCommand extends PageOpener {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String lang = req.getParameter(LANG).toUpperCase();

        ((ResourceManager) req.getSession().getAttribute(LANG)).changeResource(LanguageEnum.valueOf(lang).getLocale());

        User user = (User) req.getSession().getAttribute(USER);
        switch ((user.getRole())) {
            case USER:
                JspCommandEnum.PRIVATE.getCommand().execute(req, resp);
                break;
            case ADMIN:
                JspCommandEnum.ADMIN.getCommand().execute(req, resp);
                break;
        }
    }
}

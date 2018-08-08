package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.entity.LanguageEnum;
import by.epam.java_training.mihail_poliansky.final_project.util.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspChangeLanguageCommand extends PageOpener {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String lang = req.getParameter("lang").toUpperCase();

        ((ResourceManager) req.getSession().getAttribute("lang")).changeResource(LanguageEnum.valueOf(lang).getLocale());

        JspCommandEnum.PRIVATE.getCommand().execute(req, resp);
    }
}

package by.epam.java_training.mihail_poliansky.final_project.controller;

import by.epam.java_training.mihail_poliansky.final_project.constant.ParametersNames;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.epam.java_training.mihail_poliansky.final_project.constant.JspPages.REGISTRATION_LOGIN_JSP;

public class SessionFilter implements Filter {

    private static Logger logger = LogManager.getLogger(SessionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String action = req.getParameter(ParametersNames.ACTION);
        boolean isSessionNull = (session == null);
        String user = null;
//        if (session != null) {
//            user = session.getAttribute("user").toString();
//        }
        logger.info(action + " " + session);
        boolean doFilter = action != null && !action.equals(ParametersNames.LOGIN)
                && !action.equals(ParametersNames.REGISTRATION) &&
                isSessionNull;
        logger.info("do Filter " + doFilter);
        if (doFilter) {
            resp.sendRedirect(REGISTRATION_LOGIN_JSP);

        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }


}

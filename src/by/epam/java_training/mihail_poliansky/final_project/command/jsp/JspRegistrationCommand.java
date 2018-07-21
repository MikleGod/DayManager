package by.epam.java_training.mihail_poliansky.final_project.command.jsp;

import by.epam.java_training.mihail_poliansky.final_project.command.RegistrationCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.UserAlreadyExistsException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspRegistrationCommand extends RegistrationCommand {


    @Override
    protected void doAlternative(HttpServletRequest req, HttpServletResponse resp, UserAlreadyExistsException e) {

    }

    @Override
    protected void doAlternative(HttpServletRequest req, HttpServletResponse resp, DBException e) {

    }

    @Override
    protected void doAlternative(HttpServletRequest req, HttpServletResponse resp, ConnectionPoolException e) {

    }

    @Override
    protected void doMandatory(HttpServletRequest req, HttpServletResponse resp, User user) {

    }
}

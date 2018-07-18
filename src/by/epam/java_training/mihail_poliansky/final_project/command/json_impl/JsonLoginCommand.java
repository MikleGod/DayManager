package by.epam.java_training.mihail_poliansky.final_project.command.json_impl;

import by.epam.java_training.mihail_poliansky.final_project.command.LoginCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchUserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonLoginCommand extends LoginCommand {

    @Override
    protected void doAlternative(NoSuchUserException e, HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doAlternative(ConnectionPoolException e, HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doAlternative(DBException e, HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doMandatory(User user, HttpServletRequest req, HttpServletResponse resp) {

    }
}

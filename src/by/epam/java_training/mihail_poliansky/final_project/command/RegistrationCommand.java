package by.epam.java_training.mihail_poliansky.final_project.command;

import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.entity.UserPrivates;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.UserAlreadyExistsException;
import by.epam.java_training.mihail_poliansky.final_project.factory.EntityFactory;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RegistrationCommand implements ActionCommand{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = EntityFactory.createUser(req.getParameter("role"), req.getParameter("nickname"));
            UserPrivates privates = EntityFactory.createUserPrivates(
                    req.getParameter("mail"), req.getParameter("password").hashCode());
            ServiceFactory.getAuthService().registration(user, privates);
            doMandatory(req, resp, user);
        } catch (ConnectionPoolException e) {
            doAlternative(req, resp, e);
        } catch (DBException e) {
            doAlternative(req, resp, e);
        } catch (UserAlreadyExistsException e) {
            doAlternative(req, resp, e);
        }
    }

    protected abstract void doAlternative(HttpServletRequest req, HttpServletResponse resp, UserAlreadyExistsException e);

    protected abstract void doAlternative(HttpServletRequest req, HttpServletResponse resp, DBException e);

    protected abstract void doAlternative(HttpServletRequest req, HttpServletResponse resp, ConnectionPoolException e);

    protected abstract void doMandatory(HttpServletRequest req, HttpServletResponse resp, User user);
}

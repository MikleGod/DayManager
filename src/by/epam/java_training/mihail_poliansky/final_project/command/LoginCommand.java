package by.epam.java_training.mihail_poliansky.final_project.command;

import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchUserException;
import by.epam.java_training.mihail_poliansky.final_project.factory.EntityFactory;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class LoginCommand implements ActionCommand{
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = ServiceFactory.getAuthService()
                    .login(EntityFactory.createUserPrivates(
                            req.getParameter("mail"), req.getParameter("password").hashCode())
                    );
            doMandatory(user, req, resp);
        } catch (NoSuchUserException e){
            doAlternative(e, req, resp);
        } catch (ConnectionPoolException e) {
            doAlternative(e, req, resp);
        } catch (DBException e) {
            doAlternative(e, req, resp);
        }
    }

    protected abstract void doAlternative(NoSuchUserException e, HttpServletRequest req, HttpServletResponse resp);
    protected abstract void doAlternative(ConnectionPoolException e, HttpServletRequest req, HttpServletResponse resp);
    protected abstract void doAlternative(DBException e, HttpServletRequest req, HttpServletResponse resp);
    protected abstract void doMandatory(User user, HttpServletRequest req, HttpServletResponse resp);
}

package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.command.LoginCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.Role;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchUserException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import by.epam.java_training.mihail_poliansky.final_project.util.ExceptionStringCreator;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonLoginCommand extends LoginCommand {

    private static Logger logger = LogManager.getLogger(JsonLoginCommand.class);

    @Override
    protected void doAlternative(NoSuchUserException e, HttpServletRequest req, HttpServletResponse resp) {
        write(new Message("No such user"), resp);
    }


    @Override
    protected void doAlternative(ServiceException e, HttpServletRequest req, HttpServletResponse resp) {
        write(new Message("Smth wrong, sorry!"), resp);
    }

    @Override
    protected void doMandatory(User user, HttpServletRequest req, HttpServletResponse resp) {
        try {
            LoginJsonDto mess = new LoginJsonDto(
                    user.getId() + "",
                    user.getNickname(),
                    user.getRole(),
                    ServiceFactory.getTimeManagerService().getTMItems(user).toArray(),
                    ServiceFactory.getCashFlowService().getCFItems(user).toArray()
            );
            write(mess, resp);
        } catch (ServiceException e) {
            logger.info(ExceptionStringCreator.createString(e));
            doAlternative(e, req, resp);
        }
    }


    private void write(Object mess, HttpServletResponse resp) {
        try {
            String s = JSON.toJSONString(mess);
            logger.info(s);
            resp.getWriter().write(s);
        } catch (IOException e) {
            logger.info(ExceptionStringCreator.createString(e));
        }
    }

    static class LoginJsonDto {
        private String usr_id;
        private String usr_nickname;
        private Role role;
        private Object[] tmItems;
        private Object[] cfItems;

        public LoginJsonDto(String usr_id, String usr_nickname, Role role, Object[] tmItems, Object[] cfItems) {
            this.usr_id = usr_id;
            this.usr_nickname = usr_nickname;
            this.role = role;
            this.tmItems = tmItems;
            this.cfItems = cfItems;
        }

        public String getUsr_id() {
            return usr_id;
        }

        public void setUsr_id(String usr_id) {
            this.usr_id = usr_id;
        }

        public String getUsr_nickname() {
            return usr_nickname;
        }

        public void setUsr_nickname(String usr_nickname) {
            this.usr_nickname = usr_nickname;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Object[] getTmItems() {
            return tmItems;
        }

        public void setTmItems(Object[] tmItems) {
            this.tmItems = tmItems;
        }

        public Object[] getCfItems() {
            return cfItems;
        }

        public void setCfItems(Object[] cfItems) {
            this.cfItems = cfItems;
        }
    }
}

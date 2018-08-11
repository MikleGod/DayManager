package by.epam.java_training.mihail_poliansky.final_project.command.json;

import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import com.alibaba.fastjson.JSON;
import by.epam.java_training.mihail_poliansky.final_project.command.RegistrationCommand;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.UserAlreadyExistsException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonRegistrationCommand extends RegistrationCommand {


    @Override
    protected void doAlternative(HttpServletRequest req, HttpServletResponse resp, UserAlreadyExistsException e) {
        addExceptionMessage(e, resp);
    }

    @Override
    protected void doAlternative(HttpServletRequest req, HttpServletResponse resp, ServiceException e) {
        addExceptionMessage(e, resp);
    }

    @Override
    protected void doMandatory(HttpServletRequest req, HttpServletResponse resp, User user) {
    }


    private void addExceptionMessage(Exception e, HttpServletResponse resp){
        try {
            resp.getWriter().write(JSON.toJSONString(new Message("Что-то пошло не так, попробуйте позже")));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}

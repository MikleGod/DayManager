package by.epam.java_training.mihail_poliansky.final_project.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionCommand {
    void execute(HttpServletRequest req, HttpServletResponse resp);
}

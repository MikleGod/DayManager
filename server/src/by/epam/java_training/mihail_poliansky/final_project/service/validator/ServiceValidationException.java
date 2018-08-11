package by.epam.java_training.mihail_poliansky.final_project.service.validator;

import by.epam.java_training.mihail_poliansky.final_project.entity.UserPrivates;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.DayManagerException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;

public class ServiceValidationException extends ServiceException {
    public ServiceValidationException() {
        super();
    }

    public ServiceValidationException(String s) {
        super(s);
    }

    public ServiceValidationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ServiceValidationException(Throwable throwable) {
        super(throwable);
    }

    protected ServiceValidationException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}

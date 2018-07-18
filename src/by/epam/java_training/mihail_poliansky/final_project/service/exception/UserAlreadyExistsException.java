package by.epam.java_training.mihail_poliansky.final_project.service.exception;

public class UserAlreadyExistsException extends DayManagerException{
    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String s) {
        super(s);
    }

    public UserAlreadyExistsException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UserAlreadyExistsException(Throwable throwable) {
        super(throwable);
    }

    protected UserAlreadyExistsException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}

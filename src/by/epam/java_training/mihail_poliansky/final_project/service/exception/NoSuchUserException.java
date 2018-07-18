package by.epam.java_training.mihail_poliansky.final_project.service.exception;

public class NoSuchUserException extends DayManagerException {
    public NoSuchUserException() {
        super();
    }

    public NoSuchUserException(String s) {
        super(s);
    }

    public NoSuchUserException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NoSuchUserException(Throwable throwable) {
        super(throwable);
    }

    protected NoSuchUserException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}

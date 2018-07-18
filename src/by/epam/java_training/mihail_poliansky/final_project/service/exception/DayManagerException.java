package by.epam.java_training.mihail_poliansky.final_project.service.exception;

public class DayManagerException extends Exception{
    public DayManagerException() {
        super();
    }

    public DayManagerException(String s) {
        super(s);
    }

    public DayManagerException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DayManagerException(Throwable throwable) {
        super(throwable);
    }

    protected DayManagerException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}

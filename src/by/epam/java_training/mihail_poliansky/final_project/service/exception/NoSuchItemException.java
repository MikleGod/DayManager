package by.epam.java_training.mihail_poliansky.final_project.service.exception;

public class NoSuchItemException extends RuntimeException {
    public NoSuchItemException() {
        super();
    }

    public NoSuchItemException(String s) {
        super(s);
    }

    public NoSuchItemException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NoSuchItemException(Throwable throwable) {
        super(throwable);
    }

    protected NoSuchItemException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}

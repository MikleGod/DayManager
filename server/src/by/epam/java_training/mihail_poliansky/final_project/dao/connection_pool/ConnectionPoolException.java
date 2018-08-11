package by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool;

import by.epam.java_training.mihail_poliansky.final_project.service.exception.DayManagerException;

public class ConnectionPoolException extends DayManagerException {
    public ConnectionPoolException() {
        super();
    }

    public ConnectionPoolException(String s) {
        super(s);
    }

    public ConnectionPoolException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ConnectionPoolException(Throwable throwable) {
        super(throwable);
    }

    protected ConnectionPoolException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}

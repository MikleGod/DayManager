package by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool;

import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;

public class ConnectionManager {

    private static final int MAX_CONS = 5;
    private static final String URL = "jdbc:mysql://localhost:3306/DayManagerDB?" +
            "useUnicode=true&" +
            "useJDBCCompliantTimezoneShift=true&" +
            "useLegacyDatetimeCode=false&" +
            "serverTimezone=UTC&" +
            "characterEncoding=utf8";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "newuser";
    private static final String PASSWORD = "root";

    private static ConnectionPool pool;

    static {
        try {
            pool = new ConnectionPool(MAX_CONS, URL, DRIVER, USER, PASSWORD);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getPool(){
        return pool;
    }

    private ConnectionManager() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}

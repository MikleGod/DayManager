package by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool;

import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private final String user;
    private final String password;
    private final String url;

    private BlockingQueue<Connection> freeCon;
    private BlockingQueue<Connection> busyCon;

    ConnectionPool(int maxCons, String url, String driver, String user, String password) throws DBException {

        freeCon = new ArrayBlockingQueue<>(maxCons);
        busyCon = new ArrayBlockingQueue<>(maxCons);

        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.url = url;
        this.user = user;
        this.password = password;
        for (int i = 0; i < maxCons; i++) {
            try {
                freeCon.put(getConnection());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new DBException(e);
            }
        }
    }

    private Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public Connection retrieve() throws ConnectionPoolException {
        Connection connection = null;
        try {
            connection = freeCon.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e);
        }
        busyCon.add(connection);
        return connection;
    }

    public void putBack(Connection connection) throws DBException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new DBException(e);
        }
        busyCon.remove(connection);
        try {
            freeCon.put(connection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        for (Connection connection : freeCon) {
            connection.close();
        }
        for (Connection connection : busyCon) {
            connection.close();
        }
        super.finalize();
    }
}

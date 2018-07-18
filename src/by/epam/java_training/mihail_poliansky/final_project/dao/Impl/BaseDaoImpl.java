package by.epam.java_training.mihail_poliansky.final_project.dao.Impl;

import by.epam.java_training.mihail_poliansky.final_project.entity.HasId;
import by.epam.java_training.mihail_poliansky.final_project.entity.HasName;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchItemException;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class BaseDaoImpl {
    protected Connection initConnection() throws ConnectionPoolException {
        return ConnectionManager.getPool().retrieve();
    }
    protected void closeConnection(Connection connection) throws DBException {
        ConnectionManager.getPool().putBack(connection);
    }

    protected <T extends HasId> void com(T user, T item, String query) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(2, item.getId());
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    protected <T extends HasName> T find(T item, String query, String columnName) throws ConnectionPoolException, DBException {
        ResultSet set;
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    query
            );
            statement.setString(1, item.getName());
            set = statement.executeQuery();
            if (!set.next()){
                throw new NoSuchItemException();
            }
            item.setId(set.getInt(columnName));
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return item;
    }
}

package by.epam.java_training.mihail_poliansky.final_project.dao.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.UsersDao;
import by.epam.java_training.mihail_poliansky.final_project.entity.Role;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.ColumnNames.ROLE_ID;
import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.ColumnNames.USR_ID;
import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.ColumnNames.USR_NICKNAME;
import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.SqlQueries.*;

public class UsersDaoImpl extends BaseDaoImpl implements UsersDao {

    @Override
    public void insert(User user) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER);
            statement.setString(1, user.getNickname());
            statement.setInt(2, user.getRole().getId());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(User user) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getNickname());
            statement.setInt(2, user.getRole().getId());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(User user) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public User find(User user) throws ConnectionPoolException, DBException{
        return find(user.getId());
    }

    @Test
    public void testDB() {
        Connection connection;
        try {
            connection = initConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
            return;
        }

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER);
            statement.setString(1, "test2");
            statement.setInt(2, 1);
            statement.executeUpdate();
        } catch (SQLException e) {
        } finally {
        }
    }


    @Override
    public User find(int userId) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        ResultSet set;
        User user;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER);
            statement.setInt(1, userId);
            set = statement.executeQuery();
            set.next();
            user = new User(Role.getRole(set.getInt(ROLE_ID)), set.getString(USR_NICKNAME), set.getInt(USR_ID));
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return user;
    }
}

package by.epam.java_training.mihail_poliansky.final_project.dao.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.UserPrivatesDao;
import by.epam.java_training.mihail_poliansky.final_project.entity.UserPrivates;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchUserException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.ColumnNames.*;
import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.SqlQueries.*;

public class UsersPrivatesDaoImpl extends BaseDaoImpl implements UserPrivatesDao {

    @Override
    public void insert(UserPrivates user) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_PRIVATES);
            statement.setString(1, user.getMail());
            statement.setInt(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(UserPrivates user) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PRIVATES);
            statement.setString(1, user.getMail());
            statement.setInt(2, user.getPassword());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(UserPrivates user) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_PRIVATES);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public UserPrivates find(UserPrivates user) throws ConnectionPoolException, DBException, NoSuchUserException {
        Connection connection = initConnection();
        ResultSet set;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_USER_PRIVATES);
            statement.setInt(1, user.getPassword());
            statement.setString(2, user.getMail());
            set = statement.executeQuery();
            if (!set.next()) {
                throw new NoSuchUserException(user.getMail() + " " + user.getPassword());
            }
            user.setId(set.getInt(USR_ID));
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return user;
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

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_USER_PRIVATES);
            statement.setString(1, "test3@gmail.com");
            statement.setInt(2, "test3".hashCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public UserPrivates find(int userId) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        ResultSet set;
        UserPrivates privates;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_PRIVATES);
            statement.setInt(1, userId);
            set = statement.executeQuery();
            set.next();
            privates = new UserPrivates(set.getString(MAIL), set.getInt(PASSWORD), userId);
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return privates;
    }

    @Override
    public boolean find(String mail) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        ResultSet set;
        try {
            PreparedStatement statement = connection.prepareStatement(CHECK_MAIL_IS_VACANT);
            statement.setString(1, mail);
            set = statement.executeQuery();
            return set.next();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }
}

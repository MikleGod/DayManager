package by.epam.java_training.mihail_poliansky.final_project.dao.Impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.TimeManagerItemsDao;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.ColumnNames.*;
import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.SqlQueries.*;

class TimeManagerItemsDaoImpl extends BaseDaoImpl implements TimeManagerItemsDao {


    @Override
    public List<TimeManagerItem> findAll(User user) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        ResultSet set;
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement(SELECT_TIME_MANAGER_ITEMS);
            statement.setInt(1, user.getId());
            set = statement.executeQuery();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return parseSet(set);
    }

    @Override
    public TimeManagerItem find(TimeManagerItem item) throws ConnectionPoolException, DBException {
        return find(item, FIND_TIME_MANAGER_ITEM, TMI_ID);
    }

    @Override
    public void insert(TimeManagerItem item) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement(INSERT_TIME_MANAGE_ITEMS);
            statement.setString(1, item.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void addItem(User user, TimeManagerItem item) throws ConnectionPoolException, DBException {
        com(user, item, ADD_TO_USER_TMI);
    }

    @Override
    public void deleteItem(User user, TimeManagerItem item) throws ConnectionPoolException, DBException {
        com(user, item, DELETE_FROM_USER_TMI);
    }

    @Override
    public void update(TimeManagerItem item) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_TIME_MANAGE_ITEMS);
            statement.setInt(2, item.getId());
            statement.setString(1, item.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(TimeManagerItem item) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_TIME_MANAGE_ITEMS);
            statement.setInt(1, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    private List<TimeManagerItem> parseSet(ResultSet set) throws DBException {
        List<TimeManagerItem> items = new ArrayList<>();
        try {
            while (set.next()) {
                items.add(new TimeManagerItem(set.getString(ITEM_NAME), set.getInt(TMI_ID)));
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return items;
    }
}

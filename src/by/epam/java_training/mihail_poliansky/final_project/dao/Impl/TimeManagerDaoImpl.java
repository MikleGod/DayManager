package by.epam.java_training.mihail_poliansky.final_project.dao.Impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.TimeManagerDao;
import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.ColumnNames.*;
import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.SqlQueries.*;

class TimeManagerDaoImpl extends BaseDaoImpl implements TimeManagerDao {

    @Override
    public List<TimeManagerPlanItem> findAll(Date date, User user) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        ResultSet set;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_TIME_MANAGE);
            statement.setDate(2, date);
            statement.setInt(1, user.getId());
            set = statement.executeQuery();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return parseSet(set, date, user);
    }

    @Override
    public void update(TimeManagerPlanItem item) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_TIME_MANAGE);
            statement.setString(2, item.getTimeEnd());
            statement.setInt(3, item.getTimeManagerItem().getId());
            statement.setInt(4, item.getId());
            statement.setString(1, item.getTimeBegin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(TimeManagerPlanItem item) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_TIME_MANAGE);
            statement.setDate(2, item.getDate());
            statement.setInt(1, item.getUser().getId());
            statement.setInt(3, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void insert(List<TimeManagerPlanItem> items) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            for (TimeManagerPlanItem item : items) {
                insert(item, connection);
            }
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void insert(TimeManagerPlanItem item) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            insert(item, connection);
        } finally {
            closeConnection(connection);
        }
    }

    private void insert(TimeManagerPlanItem item, Connection connection) throws DBException {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_TIME_MANAGE);
            statement.setDate(2, item.getDate());
            statement.setInt(1, item.getUser().getId());
            statement.setInt(5, item.getTimeManagerItem().getId());
            statement.setString(3, item.getTimeBegin());
            statement.setString(4, item.getTimeBegin());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private List<TimeManagerPlanItem> parseSet(ResultSet set, Date date, User user) throws DBException {
        List<TimeManagerPlanItem> items = new ArrayList<>();
        try {

            while (set.next()) {
                items.add(
                        new TimeManagerPlanItem(new TimeManagerItem(set.getString(ITEM_NAME), set.getInt(TMI_ID))
                                , set.getString("time_begin")
                                , set.getString("time_end")
                                , user
                                , date
                                , set.getInt("tm_id")
                        )
                );
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return items;
    }
}

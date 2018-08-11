package by.epam.java_training.mihail_poliansky.final_project.dao.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.ActivitiesDao;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.constant.SqlQueries;
import by.epam.java_training.mihail_poliansky.final_project.entity.ActivityEnum;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.SqlQueries.SELECT_TIME_MANAGER_ITEMS;

public class ActivitiesDaoImpl extends BaseDaoImpl implements ActivitiesDao {

    @Override
    public List<ActivityEnum> findActivities(User user) throws DBException, ConnectionPoolException {
        Connection connection = initConnection();
        ResultSet set;
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement(SqlQueries.SELECT_ALL_ACTIVITIES_USER_HAS);
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
    public void insert(User user, ActivityEnum activity) throws DBException, ConnectionPoolException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement(SqlQueries.ADD_ACTIVITY_TO_USER);
            statement.setInt(1, user.getId());
            statement.setInt(2, activity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<ActivityEnum> findAll() throws DBException, ConnectionPoolException {
        Connection connection = initConnection();
        ResultSet set;
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement(SqlQueries.SELECT_ALL_ACTIVITIES);
            set = statement.executeQuery();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return parseSet(set);
    }

    @Override
    public void delete(User user, ActivityEnum activity) throws DBException, ConnectionPoolException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement(SqlQueries.DELETE_ACTIVITY_FROM_USER);
            statement.setInt(1, user.getId());
            statement.setInt(2, activity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    private List<ActivityEnum> parseSet(ResultSet set) throws DBException {
        List<ActivityEnum> activities = new ArrayList<>();
        try {
            while (set.next()) {
                activities.add(ActivityEnum.valueOf(set.getString("name").toUpperCase()));
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return activities;
    }
}

package by.epam.java_training.mihail_poliansky.final_project.dao;

import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.entity.ActivityEnum;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;

import java.util.List;

public interface ActivitiesDao {
    List<ActivityEnum> findActivities(User user) throws DBException, ConnectionPoolException;

    void insert(User user, ActivityEnum activity) throws DBException, ConnectionPoolException;

    List<ActivityEnum> findAll() throws DBException, ConnectionPoolException;

    void delete(User user, ActivityEnum activity) throws DBException, ConnectionPoolException;
}

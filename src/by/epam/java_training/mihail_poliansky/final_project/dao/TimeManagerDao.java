package by.epam.java_training.mihail_poliansky.final_project.dao;

import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;

import java.sql.Date;
import java.util.List;

public interface TimeManagerDao {
    List<TimeManagerPlanItem> findAll(Date date, User user) throws ConnectionPoolException, DBException;
    List<TimeManagerPlanItem> findAll(User user) throws ConnectionPoolException, DBException;
    void update(TimeManagerPlanItem item) throws ConnectionPoolException, DBException;
    void delete(TimeManagerPlanItem item) throws ConnectionPoolException, DBException;
    void insert(List<TimeManagerPlanItem> items) throws ConnectionPoolException, DBException;
    void insert(TimeManagerPlanItem item) throws ConnectionPoolException, DBException;
}

package by.epam.java_training.mihail_poliansky.final_project.dao;

import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;

import java.util.List;

public interface TimeManagerItemsDao {
    List<TimeManagerItem> findAll(User user) throws ConnectionPoolException, DBException;
    TimeManagerItem find(TimeManagerItem item) throws ConnectionPoolException, DBException;
    void insert(TimeManagerItem item) throws ConnectionPoolException, DBException;
    void addItem(User user, TimeManagerItem item) throws ConnectionPoolException, DBException;
    void deleteItem(User user, TimeManagerItem item) throws ConnectionPoolException, DBException;
    void update(TimeManagerItem item) throws ConnectionPoolException, DBException;
    void delete(TimeManagerItem item) throws ConnectionPoolException, DBException;
}

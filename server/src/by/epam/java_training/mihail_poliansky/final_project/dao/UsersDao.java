package by.epam.java_training.mihail_poliansky.final_project.dao;

import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;

import java.util.List;

public interface UsersDao {
    void insert(User user) throws ConnectionPoolException, DBException;
    void update(User user) throws ConnectionPoolException, DBException;
    void delete(User user) throws ConnectionPoolException, DBException;
    User find(User user) throws ConnectionPoolException, DBException;
    User find(int userId) throws ConnectionPoolException, DBException;
    List<User> findAll() throws ConnectionPoolException, DBException;
}

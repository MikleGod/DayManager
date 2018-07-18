package by.epam.java_training.mihail_poliansky.final_project.dao;

import by.epam.java_training.mihail_poliansky.final_project.entity.UserPrivates;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchUserException;

public interface UserPrivatesDao {
    void insert(UserPrivates user) throws ConnectionPoolException, DBException;
    void update(UserPrivates user) throws ConnectionPoolException, DBException;
    void delete(UserPrivates user) throws ConnectionPoolException, DBException;
    UserPrivates find(UserPrivates user) throws ConnectionPoolException, DBException, NoSuchUserException;
    UserPrivates find(int userId) throws ConnectionPoolException, DBException;
}

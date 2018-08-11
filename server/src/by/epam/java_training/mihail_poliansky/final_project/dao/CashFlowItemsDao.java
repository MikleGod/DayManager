package by.epam.java_training.mihail_poliansky.final_project.dao;

import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchItemException;

import java.util.List;

public interface CashFlowItemsDao {
    List<CashFlowItem> findAll(User user) throws ConnectionPoolException, DBException;
    CashFlowItem find(CashFlowItem item) throws ConnectionPoolException, DBException, NoSuchItemException;
    void insert(CashFlowItem item) throws ConnectionPoolException, DBException;
    void addItem(User user, CashFlowItem item) throws ConnectionPoolException, DBException;
    void deleteItem(User user, CashFlowItem item) throws ConnectionPoolException, DBException;
    void delete(CashFlowItem item) throws ConnectionPoolException, DBException;
}

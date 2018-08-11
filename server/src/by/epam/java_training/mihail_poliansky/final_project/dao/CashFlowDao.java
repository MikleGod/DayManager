package by.epam.java_training.mihail_poliansky.final_project.dao;

import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;

import java.sql.Date;
import java.util.List;

public interface CashFlowDao {
    List<CashFlowPlanItem> findAll(Date date, User user) throws ConnectionPoolException, DBException;
    List<CashFlowPlanItem> findAll(User user, CashFlowItem item) throws ConnectionPoolException, DBException;
    void update(CashFlowPlanItem item) throws ConnectionPoolException, DBException;
    void delete(CashFlowPlanItem item) throws ConnectionPoolException, DBException;
    void insert(List<CashFlowPlanItem> items) throws ConnectionPoolException, DBException;
    void insert(CashFlowPlanItem item) throws ConnectionPoolException, DBException;
}

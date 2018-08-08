package by.epam.java_training.mihail_poliansky.final_project.dao.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.CashFlowItemsDao;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchItemException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.ColumnNames.*;
import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.SqlQueries.*;

class CashFlowItemsDaoImpl extends BaseDaoImpl implements CashFlowItemsDao {

    @Override
    public List<CashFlowItem> findAll(User user) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        ResultSet set;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_CASH_FLOW_ITEMS);
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
    public CashFlowItem find(CashFlowItem item) throws ConnectionPoolException, DBException, NoSuchItemException {
        return find(item, FIND_CASH_FLOW_ITEM, CFI_ID);
    }

    @Override
    public void insert(CashFlowItem item) throws ConnectionPoolException, DBException  {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_CASH_FLOW_ITEMS);
            statement.setString(1, item.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void addItem(User user, CashFlowItem item) throws ConnectionPoolException, DBException {
        com(user, item, ADD_TO_USER_CFI);
    }

    @Override
    public void deleteItem(User user, CashFlowItem item) throws ConnectionPoolException, DBException {
        com(user, item, DELETE_FROM_USER_CFI);
    }


    @Override
    public void delete(CashFlowItem item) throws ConnectionPoolException, DBException  {
        Connection connection = initConnection();
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement(DELETE_CASH_FLOW_ITEMS);
            statement.setInt(1, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    private List<CashFlowItem> parseSet(ResultSet set) throws DBException {
        List<CashFlowItem> items = new ArrayList<>();
        try {

            while (set.next()) {
                items.add(new CashFlowItem(set.getString(ITEM_NAME), set.getInt(2)));
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return items;
    }
}

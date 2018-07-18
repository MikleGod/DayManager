package by.epam.java_training.mihail_poliansky.final_project.dao.Impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.CashFlowDao;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.SqlQueries.*;
import static by.epam.java_training.mihail_poliansky.final_project.dao.constant.ColumnNames.*;

class CashFlowDaoImpl extends BaseDaoImpl implements CashFlowDao {

    @Override
    public List<CashFlowPlanItem> findAll(Date date, User user) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        ResultSet set;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_CASH_FLOW);
            statement.setInt(1, user.getId());
            statement.setDate(2, date);
            set = statement.executeQuery();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return parseSet(set, date, user);
    }

    @Override
    public void update(CashFlowPlanItem item) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_CASH_FLOW);
            statement.setInt(2, item.getCashFlowItem().getId());
            statement.setInt(3, item.getId());
            statement.setDouble(1, item.getCost());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(CashFlowPlanItem item) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_CASH_FLOW);
            statement.setInt(3, item.getId());
            statement.setDate(2, item.getDate());
            statement.setInt(1, item.getUser().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void insert(List<CashFlowPlanItem> items) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            for (CashFlowPlanItem item : items) {
                insert(item, connection);
            }
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void insert(CashFlowPlanItem item) throws ConnectionPoolException, DBException {
        Connection connection = initConnection();
        try {
            insert(item, connection);
        } finally {
            closeConnection(connection);
        }
    }

    private void insert(CashFlowPlanItem item, Connection connection) throws DBException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_CASH_FLOW);
            statement.setDate(2, item.getDate());
            statement.setInt(1, item.getUser().getId());
            statement.setInt(4, item.getCashFlowItem().getId());
            statement.setDouble(3, item.getCost());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private List<CashFlowPlanItem> parseSet(ResultSet set, Date date, User user) throws DBException {
        List<CashFlowPlanItem> items = new ArrayList<>();
        try {

            while (set.next()) {
                items.add(
                        new CashFlowPlanItem(set.getInt(CF_ID)
                                , new CashFlowItem(set.getString(ITEM_NAME), set.getInt(CFI_ID))
                                , set.getDouble(COST)
                                , date
                                , user
                        )
                );
            }

        } catch (SQLException e) {
            throw new DBException(e);
        }
        return items;
    }
}

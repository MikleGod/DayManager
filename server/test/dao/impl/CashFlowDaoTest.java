package dao.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.CashFlowDao;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.impl.DaoFactory;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowPlanItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.Role;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;

public class CashFlowDaoTest extends Assert {
    private CashFlowDao cashFlowDao = DaoFactory.getCashFlowDao();

    @Test
    public void testFindAll() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        Date date = new Date(2018-1900, 7, 7);
        int expectedListSize = 1;

        int actualListSize = cashFlowDao.findAll(date, user).size();

        assertEquals(expectedListSize, actualListSize);
    }

    @Test
    @Ignore
    public void testInsert() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        Date date = new Date(2018-1900, 7, 7);
        CashFlowItem cashFlowItem = new CashFlowItem("Транспорт", 1);
        CashFlowPlanItem item = new CashFlowPlanItem(4, cashFlowItem, 23, date, user);
        int expectedListSize = 2;

        cashFlowDao.insert(item);
        int actualListSize = cashFlowDao.findAll(date, user).size();

        assertEquals(expectedListSize, actualListSize);
    }

    @Test
    @Ignore
    public void testUpdate() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        Date date = new Date(2018-1900, 7, 7);
        CashFlowItem cashFlowItem = new CashFlowItem("Транспорт", 1);
        CashFlowPlanItem item = new CashFlowPlanItem(4, cashFlowItem, 23, date, user);
        double expectedCost = 23;

        cashFlowDao.update(item);
        double actualCost = cashFlowDao.findAll(date, user).get(1).getCost();

        assertEquals(expectedCost, actualCost, 0.0001);
    }

    @Test
    @Ignore
    public void testDelete() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        Date date = new Date(2018-1900, 7, 7);
        CashFlowItem cashFlowItem = new CashFlowItem("Транспорт", 1);
        CashFlowPlanItem item = new CashFlowPlanItem(4, cashFlowItem, 23, date, user);
        int expectedSize = 1;

        cashFlowDao.delete(item);
        int actualSize = cashFlowDao.findAll(date, user).size();

        assertEquals(expectedSize, actualSize);
    }


}

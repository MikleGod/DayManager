package dao.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.CashFlowItemsDao;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.impl.DaoFactory;
import by.epam.java_training.mihail_poliansky.final_project.entity.CashFlowItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.Role;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class CashFlowItemsDaoTest extends Assert {
    private CashFlowItemsDao cashFlowItemsDao = DaoFactory.getCashFlowItemsDao();


    @Test
    public void testFindAll() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        int expectedItemsCount = 3;

        int actualItemsCount = cashFlowItemsDao.findAll(user).size();

        assertEquals(expectedItemsCount, actualItemsCount);
    }

    @Test
    public void testFindItem() throws DBException, ConnectionPoolException {
        CashFlowItem expectedItem = new CashFlowItem("транспорт", 1);

        CashFlowItem actualItem = cashFlowItemsDao.find(expectedItem);

        assertEquals(expectedItem, actualItem);
    }

    @Test
    public void testAddItem() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        CashFlowItem item = new CashFlowItem("налоги", 4);
        int expectedItemsCount = 4;

        cashFlowItemsDao.addItem(user, item);
        int actualItemsCount = cashFlowItemsDao.findAll(user).size();

        assertEquals(expectedItemsCount, actualItemsCount);
    }

    @Test
    public void testDeleteItem() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        CashFlowItem item = new CashFlowItem("налоги", 4);
        int expectedItemsCount = 3;

        cashFlowItemsDao.deleteItem(user, item);
        int actualItemsCount = cashFlowItemsDao.findAll(user).size();

        assertEquals(expectedItemsCount, actualItemsCount);
    }

    @Test
    @Ignore
    public void testInsert() throws DBException, ConnectionPoolException {
        CashFlowItem expectedItem = new CashFlowItem("бухлишко", 7);

        cashFlowItemsDao.insert(expectedItem);

        //assertEquals(expectedItem, actualItem);
    }

    @Test
    @Ignore
    public void testDelete() throws DBException, ConnectionPoolException {
        CashFlowItem expectedItem = new CashFlowItem("бухлишко", 7);

        cashFlowItemsDao.delete(expectedItem);

        //assertEquals(expectedItem, actualItem);
    }
}

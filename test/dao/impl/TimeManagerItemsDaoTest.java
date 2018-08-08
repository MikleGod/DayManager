package dao.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.dao.TimeManagerItemsDao;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.impl.DaoFactory;
import by.epam.java_training.mihail_poliansky.final_project.entity.Role;
import by.epam.java_training.mihail_poliansky.final_project.entity.TimeManagerItem;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class TimeManagerItemsDaoTest extends Assert {
    private TimeManagerItemsDao timeManagerItemsDao = DaoFactory.getTimeManagerItemsDao();


    @Test
    public void testFindAll() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        int expectedItemsCount = 3;

        int actualItemsCount = timeManagerItemsDao.findAll(user).size();

        assertEquals(expectedItemsCount, actualItemsCount);
    }

    @Test
    public void testFindItem() throws DBException, ConnectionPoolException {
        TimeManagerItem expectedItem = new TimeManagerItem("транспорт", 1);

        TimeManagerItem actualItem = timeManagerItemsDao.find(expectedItem);

        assertEquals(expectedItem, actualItem);
    }

    @Test
    public void testAddItem() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        TimeManagerItem item = new TimeManagerItem("прогулки", 4);
        int expectedItemsCount = 4;

        timeManagerItemsDao.addItem(user, item);
        int actualItemsCount = timeManagerItemsDao.findAll(user).size();

        assertEquals(expectedItemsCount, actualItemsCount);
    }

    @Test
    public void testDeleteItem() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        TimeManagerItem item = new TimeManagerItem("прогулки", 4);
        int expectedItemsCount = 3;

        timeManagerItemsDao.deleteItem(user, item);
        int actualItemsCount = timeManagerItemsDao.findAll(user).size();

        assertEquals(expectedItemsCount, actualItemsCount);
    }

    @Test
    @Ignore
    public void testInsert() throws DBException, ConnectionPoolException {
        TimeManagerItem expectedItem = new TimeManagerItem("бухлишко", 6);

        timeManagerItemsDao.insert(expectedItem);

        //assertEquals(expectedItem, actualItem);
    }

    @Test
    @Ignore
    public void testDelete() throws DBException, ConnectionPoolException {
        TimeManagerItem expectedItem = new TimeManagerItem("бухлишко", 6);

        timeManagerItemsDao.delete(expectedItem);

        //assertEquals(expectedItem, actualItem);
    }
}

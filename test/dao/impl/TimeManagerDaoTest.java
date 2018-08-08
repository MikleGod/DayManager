package dao.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.dao.TimeManagerDao;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.impl.DaoFactory;
import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Date;

public class TimeManagerDaoTest extends Assert {
    private TimeManagerDao timeManagerDao = DaoFactory.getTimeManagerDao();

    @Test
    public void testFindAll() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        Date date = new Date(2018-1900, 7, 7);
        int expectedListSize;
        expectedListSize = 1;

        int actualListSize = timeManagerDao.findAll(date, user).size();

        assertEquals(expectedListSize, actualListSize);
    }

    @Test
    @Ignore
    public void testInsert() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        Date date = new Date(2018-1900, 7, 7);
        TimeManagerItem timeManagerItem;
        timeManagerItem = new TimeManagerItem("Транспорт", 1);
        TimeManagerPlanItem item = new TimeManagerPlanItem(timeManagerItem, "", "", user, date, 3);
        int expectedListSize = 2;

        timeManagerDao.insert(item);
        int actualListSize = timeManagerDao.findAll(date, user).size();

        assertEquals(expectedListSize, actualListSize);
    }

    @Test
    @Ignore
    public void testUpdate() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        Date date = new Date(2018-1900, 7, 7);
        TimeManagerItem timeManagerItem;
        timeManagerItem = new TimeManagerItem("Транспорт", 1);
        TimeManagerPlanItem item = new TimeManagerPlanItem(timeManagerItem, "18-00", "21-00", user, date, 3);
        String expectedEnd = "21-00";

        timeManagerDao.update(item);
        String actualTimeEnd = timeManagerDao.findAll(date, user).get(1).getTimeEnd();

        assertEquals(expectedEnd, actualTimeEnd);
    }

    @Test
    @Ignore
    public void testDelete() throws DBException, ConnectionPoolException {
        User user = new User(Role.USER, "usr1", 1);
        Date date = new Date(2018-1900, 7, 7);
        TimeManagerItem timeManagerItem;
        timeManagerItem = new TimeManagerItem("Транспорт", 1);
        TimeManagerPlanItem item = new TimeManagerPlanItem(timeManagerItem, "18-00", "21-00", user, date, 3);
        int expectedSize = 1;

        timeManagerDao.delete(item);
        int actualSize = timeManagerDao.findAll(date, user).size();

        assertEquals(expectedSize, actualSize);
    }

}

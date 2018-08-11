package dao.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.dao.UserPrivatesDao;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.impl.DaoFactory;
import by.epam.java_training.mihail_poliansky.final_project.entity.UserPrivates;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.NoSuchUserException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class UsersPrivatesDaoTest extends Assert {

    private UserPrivatesDao userPrivatesDao = DaoFactory.getUserPrivatesDao();


    @Test
    public void testMailIsVacant() throws DBException, ConnectionPoolException {
        String mail = "usr1@mail.by";

        boolean isVacant = !userPrivatesDao.find(mail);

        assertFalse(isVacant);
    }

    @Test
    public void testFindPrivatesById() throws DBException, ConnectionPoolException {
        String mail = "usr1@mail.by";

        UserPrivates actual = userPrivatesDao.find(1);

        assertEquals(mail, actual.getMail());
    }

    @Test
    public void testFindPrivatesByMailAndPassword() throws DBException, ConnectionPoolException, NoSuchUserException {
        String mail = "usr1@mail.by";
        int password = 110251487;

        UserPrivates actual = userPrivatesDao.find(new UserPrivates(mail, password));

        assertEquals(1, actual.getId() );
    }

    @Test
    @Ignore
    public void testInsert() throws DBException, ConnectionPoolException, NoSuchUserException {
        String mail = "usr15@mail.by";
        int password = 110251487;
        int id = 15;

        userPrivatesDao.insert(new UserPrivates(mail, password));

        UserPrivates actual = userPrivatesDao.find(id);
        assertEquals(mail, actual.getMail());
    }

    @Test
    @Ignore
    public void testUpdate() throws DBException, ConnectionPoolException, NoSuchUserException {
        String mail = "usr15@mail.by";
        int password = 110251488;
        int id = 15;

        userPrivatesDao.update(new UserPrivates(mail, password, id));

        UserPrivates actual = userPrivatesDao.find(id);
        assertEquals(password, actual.getPassword());
    }

    @Test
    @Ignore
    public void testDelete() throws DBException, ConnectionPoolException, NoSuchUserException {
        String mail = "usr15@mail.by";
        int password = 110251488;
        int id = 15;

        userPrivatesDao.delete(new UserPrivates(mail, password, id));

        UserPrivates actual = userPrivatesDao.find(id);
        assertNull(actual);
    }
}

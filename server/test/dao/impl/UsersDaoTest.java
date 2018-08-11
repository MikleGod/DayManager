package dao.impl;

import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.dao.UsersDao;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.impl.DaoFactory;
import by.epam.java_training.mihail_poliansky.final_project.entity.Role;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class UsersDaoTest extends Assert {

    private UsersDao usersDao = DaoFactory.getUsersDao();

    @Test
    public void testFindExistingUser(){
        User expectedUser = new User(Role.USER, "usr1", 1);

        User actualUser = null;
        try {
            actualUser = usersDao.find(expectedUser);
        } catch (ConnectionPoolException | DBException e) {
            e.printStackTrace();
        }

        assertNotNull(actualUser);
        assertEquals(actualUser.getId(), expectedUser.getId());
    }

    @Test
    public void testFindNotExistingUser(){
        User findingUser = new User(Role.USER, "kish", 1023);

        User actualUser = null;
        try {
            actualUser = usersDao.find(findingUser);
        } catch (ConnectionPoolException | DBException e) {
            e.printStackTrace();
        }

        assertNull(actualUser);
    }

    @Test
    @Ignore
    public void testInsert(){
        User expectedUser = new User(Role.USER, "usr1023", 15);

        try {
            usersDao.insert(expectedUser);


            assertEquals(usersDao.find(expectedUser).getNickname(), expectedUser.getNickname());

        } catch (ConnectionPoolException | DBException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void testUpdate(){
        User expectedUser = new User(Role.USER, "usr1025", 15);

        try {
            usersDao.update(expectedUser);


            assertEquals(usersDao.find(expectedUser).getNickname(), expectedUser.getNickname());

        } catch (ConnectionPoolException | DBException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Ignore
    public void testDelete(){
        User expectedUser = new User(Role.USER, "usr1023", 15);

        try {
            usersDao.delete(expectedUser);


            assertNull(usersDao.find(expectedUser));

        } catch (ConnectionPoolException | DBException e) {
            e.printStackTrace();
        }
    }
}

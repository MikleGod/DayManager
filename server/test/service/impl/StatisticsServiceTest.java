package service.impl;

import by.epam.java_training.mihail_poliansky.final_project.entity.Role;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.service.StatisticsService;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;
import by.epam.java_training.mihail_poliansky.final_project.service.impl.ServiceFactory;
import org.junit.Assert;
import org.junit.Test;

public class StatisticsServiceTest extends Assert {

    StatisticsService statisticsService = ServiceFactory.getStatisticsService();

    @Test
    public void testCountSpentMoney() throws ServiceException {
        User user = new User(Role.USER, "  ", 1);

        assertEquals(statisticsService.countSpentMoney(user).get(0).getCost(), 120, 0.01);
    }

    @Test
    public void testService() throws ServiceException {
        User user = new User(Role.USER, "  ", 1);

        //assertEquals(statisticsService.countSpentTime(user).get(0).getTime(), , 0.01);
    }
}

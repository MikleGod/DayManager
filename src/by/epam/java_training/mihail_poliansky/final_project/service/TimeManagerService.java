package by.epam.java_training.mihail_poliansky.final_project.service;

import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public interface TimeManagerService {

    TimeManagerItem changeTMItem(User user, TimeManagerItem item) throws ServiceException;
    TimeManagerItem addTMItem(User user, TimeManagerItem item) throws ServiceException;
    void deleteTMItem(User user, TimeManagerItem item) throws ServiceException;
    List<TimeManagerItem> getTMItems(User user) throws ServiceException;

    List<TimeManagerPlanItem> getPlan(User user, Date date) throws ServiceException;
    void deletePlan(User user, Date date) throws ServiceException;
    List<TimeManagerPlanItem> changePlan(List<TimeManagerPlanItem> plan) throws ServiceException;
    List<TimeManagerPlanItem> addPlan(List<TimeManagerPlanItem> plan) throws ServiceException;
}

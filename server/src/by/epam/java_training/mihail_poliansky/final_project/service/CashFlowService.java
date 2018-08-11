package by.epam.java_training.mihail_poliansky.final_project.service;

import by.epam.java_training.mihail_poliansky.final_project.entity.*;
import by.epam.java_training.mihail_poliansky.final_project.dao.connection_pool.ConnectionPoolException;
import by.epam.java_training.mihail_poliansky.final_project.dao.DBException;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public interface CashFlowService {

    CashFlowItem changeCFItem(User user, CashFlowItem item) throws ServiceException;
    CashFlowItem addCFItem(User user, CashFlowItem item) throws ServiceException;
    void deleteCFItem(User user, CashFlowItem item) throws ServiceException;
    List<CashFlowItem> getCFItems(User user) throws ServiceException;

    List<CashFlowPlanItem> getCF(User user, Date date) throws ServiceException;
    void deleteCF(User user, Date date) throws ServiceException;
    List<CashFlowPlanItem> changeCF(List<CashFlowPlanItem> plan) throws ServiceException;
    List<CashFlowPlanItem> addCF(List<CashFlowPlanItem> plan) throws ServiceException;
    CashFlowPlanItem addCF(CashFlowPlanItem plan) throws ServiceException;
}

package by.epam.java_training.mihail_poliansky.final_project.service;

import by.epam.java_training.mihail_poliansky.final_project.entity.ActivityEnum;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.entity.UserActivitiesDto;
import by.epam.java_training.mihail_poliansky.final_project.service.exception.ServiceException;

import java.util.List;

public interface AdminService {

    List<UserActivitiesDto> findAllUsersAndActs() throws ServiceException;

    List<ActivityEnum> findAllActivities() throws ServiceException;

    void addActivity(User user, ActivityEnum activity) throws ServiceException;

    void deleteActivity(User user, ActivityEnum activity) throws ServiceException;
}

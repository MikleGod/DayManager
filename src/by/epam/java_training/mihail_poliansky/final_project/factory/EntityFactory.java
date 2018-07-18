package by.epam.java_training.mihail_poliansky.final_project.factory;

import by.epam.java_training.mihail_poliansky.final_project.entity.Role;
import by.epam.java_training.mihail_poliansky.final_project.entity.User;
import by.epam.java_training.mihail_poliansky.final_project.entity.UserPrivates;

public class EntityFactory {
    public static UserPrivates createUserPrivates(String mail, int password){
        return new UserPrivates(mail, password);
    }
    public static User createUser(String role, String nickname){
        return new User(role != null ? Role.valueOf(role):Role.USER, nickname);
    }
}

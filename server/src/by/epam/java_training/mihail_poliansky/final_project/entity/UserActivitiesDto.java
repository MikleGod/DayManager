package by.epam.java_training.mihail_poliansky.final_project.entity;

import java.util.List;
import java.util.Objects;

public class UserActivitiesDto {
    private User user;
    private List<ActivityEnum> activities;

    public UserActivitiesDto(User user, List<ActivityEnum> activities) {
        this.user = user;
        this.activities = activities;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ActivityEnum> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityEnum> activities) {
        this.activities = activities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserActivitiesDto that = (UserActivitiesDto) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(activities, that.activities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, activities);
    }
}

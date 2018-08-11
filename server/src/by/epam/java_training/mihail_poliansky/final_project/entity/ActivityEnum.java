package by.epam.java_training.mihail_poliansky.final_project.entity;

public enum ActivityEnum {
    ADD_TMI(1), ADD_CFI(3), WATCH_STAT(5), ADD_TMPI(2), ADD_CFPI(4);
    private final int id;

    ActivityEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

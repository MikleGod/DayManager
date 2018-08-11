package by.epam.java_training.mihail_poliansky.final_project.entity;

public enum  BaseTmi {
    TRANSPORT(new TimeManagerItem("транспорт", 1)),
    LEARNING(new TimeManagerItem("учеба", 2)),
    WORKING(new TimeManagerItem("работа", 3));

    private final TimeManagerItem item;

    BaseTmi(TimeManagerItem item) {
        this.item = item;
    }

    public TimeManagerItem getItem() {
        return item;
    }
}

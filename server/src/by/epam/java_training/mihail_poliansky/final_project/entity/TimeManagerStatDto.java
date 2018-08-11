package by.epam.java_training.mihail_poliansky.final_project.entity;

import java.util.Objects;

public class TimeManagerStatDto {
    private TimeManagerItem tmi;
    private String time;

    public TimeManagerStatDto(TimeManagerItem tmi, String time) {
        this.tmi = tmi;
        this.time = time;
    }

    public TimeManagerItem getTmi() {
        return tmi;
    }

    public void setTmi(TimeManagerItem tmi) {
        this.tmi = tmi;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeManagerStatDto that = (TimeManagerStatDto) o;
        return Objects.equals(tmi, that.tmi) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tmi, time);
    }
}

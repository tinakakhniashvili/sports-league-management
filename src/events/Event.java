package events;

import java.time.LocalDateTime;

public class Event {

    private static final String DEFAULT_DESCRIPTION = "No description";

    private int id;
    private LocalDateTime dateTime;
    private String description;

    static {
        System.out.println("Event class loaded");
    }

    public Event(int id, LocalDateTime dateTime, String description) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description == null ? DEFAULT_DESCRIPTION : description;
    }

    public static boolean isValidDescription(String text) {
        return text != null && !text.trim().isEmpty();
    }

    public void startEvent() {
        System.out.println("Starting event: " + toString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event: id=" + id + ", dateTime='" + dateTime + '\'' + ", description='" + description + '\'';
    }
}

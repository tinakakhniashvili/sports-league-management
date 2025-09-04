package event;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {

    private static final String DEFAULT_DESCRIPTION = "No description";

    private final int id;
    private LocalDateTime dateTime;
    private String description = DEFAULT_DESCRIPTION;

    static {
        System.out.println("Event class loaded");
    }

    public Event(int id) {
        this.id = id;
    }

    public void startEvent() {
        System.out.println("Starting event: " + this);
    }

    public void reschedule(LocalDateTime when){
        this.dateTime = Objects.requireNonNull(when, "datetime cannot be null");
    }

    public void updateDescription(String text) {
        this.description = text.trim();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Event: id=" + id + ", dateTime='" + dateTime + '\'' + ", description='" + description + '\'';
    }
}

package event;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class Event {

    private static final String DEFAULT_DESCRIPTION = "No description";

    private final Integer id;
    private LocalDateTime dateTime;
    private String description = DEFAULT_DESCRIPTION;

    static {
        System.out.println("Event class loaded");
    }

    public Event(Integer id, LocalDateTime dateTime) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
        this.dateTime = Objects.requireNonNull(dateTime, "dateTime cannot be null");
    }

    public Event(Integer id) {
        this(id, LocalDateTime.now());
    }

    public abstract BigDecimal priceMultiplier();

    protected void startEvent() {
        System.out.println("Starting event: " + this);
    }

    protected void reschedule(LocalDateTime when) {
        this.dateTime = Objects.requireNonNull(when, "datetime cannot be null");
    }

    protected void updateDescription(String text) {
        this.description = text != null ? text.trim() : DEFAULT_DESCRIPTION;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Event: id=%d, dateTime=%s, description='%s'", id, dateTime, description);
    }
}
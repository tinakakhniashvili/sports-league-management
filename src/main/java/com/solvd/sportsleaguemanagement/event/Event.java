package com.solvd.sportsleaguemanagement.event;

import com.solvd.sportsleaguemanagement.contracts.Identifiable;
import com.solvd.sportsleaguemanagement.contracts.Schedulable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Event implements Schedulable, Identifiable {

    private static final Logger LOGGER = LogManager.getLogger(Event.class);

    private static final String DEFAULT_DESCRIPTION = "No description";
    private static final String DEFAULT_TITLE = "Untitled";

    private final Integer id;
    private LocalDateTime dateTime;
    private LocalDateTime endTime;
    private String title = DEFAULT_TITLE;
    private String description = DEFAULT_DESCRIPTION;

    static {
        LogManager.getLogger(Event.class).debug("Event class loaded");
    }

    public Event(Integer id, LocalDateTime dateTime) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
        this.dateTime = Objects.requireNonNull(dateTime, "dateTime cannot be null");
        this.endTime = this.dateTime.plusHours(2);
    }

    public Event(Integer id) {
        this(id, LocalDateTime.now());
    }

    public abstract BigDecimal priceMultiplier();

    protected void startEvent() {
        LOGGER.info("Starting event: {}", this);
    }

    protected void reschedule(LocalDateTime when) {
        this.dateTime = Objects.requireNonNull(when, "datetime cannot be null");

        if (endTime != null && !endTime.isBefore(dateTime)) {

            long hours = java.time.Duration.between(getStartTime(), getEndTime()).toHours();
            this.endTime = this.dateTime.plusHours(hours);
        } else {
            this.endTime = this.dateTime.plusHours(2);
        }
    }

    protected void updateDescription(String text) {
        this.description = text != null ? text.trim() : DEFAULT_DESCRIPTION;
    }

    public Integer getIdNumber() {
        return id;
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    @Override
    public LocalDateTime getStartTime() {
        return dateTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = Objects.requireNonNull(endTime, "endTime cannot be null");
    }

    public void setTitle(String title) {
        this.title = (title == null || title.isBlank()) ? DEFAULT_TITLE : title.trim();
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
        if (!(o instanceof Event event)) return false;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Event: id=%d, dateTime=%s, endTime=%s, title='%s', description='%s'",
                id, dateTime, endTime, title, description);
    }
}

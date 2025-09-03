package events;

public class Event {
    private int id;
    private String dateTime;
    private String description;

    private static final String DEFAULT_DESCRIPTION = "No description";

    static {
        System.out.println("Event class loaded");
    }

    public Event(int id, String dateTime, String description) {
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
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

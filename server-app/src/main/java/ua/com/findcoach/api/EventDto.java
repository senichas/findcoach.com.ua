package ua.com.findcoach.api;

public class EventDto {

    private Integer eventId;

    private String title;

    private String description;

    private String typeLocalized;

    private String location;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeLocalized() {
        return typeLocalized;
    }

    public void setTypeLocalized(String typeLocalized) {
        this.typeLocalized = typeLocalized;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

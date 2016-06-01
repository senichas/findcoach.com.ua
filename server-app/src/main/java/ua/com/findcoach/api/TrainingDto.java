package ua.com.findcoach.api;

import java.time.LocalDateTime;

public class TrainingDto {
    private Integer eventId;

    private Integer eventRecurrenceId;

    private String title;

    private String description;

    private String typeLocalized;

    private String location;

    private Boolean allDay;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getEventRecurrenceId() {
        return eventRecurrenceId;
    }

    public void setEventRecurrenceId(Integer eventRecurrenceId) {
        this.eventRecurrenceId = eventRecurrenceId;
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

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}

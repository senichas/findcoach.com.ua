package ua.com.findcoach.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ua.com.findcoach.api.serializers.DateTimeSerializer;

import java.time.LocalDateTime;

public class CalendarEvent {
    private Integer id;
    private String title;
    @JsonSerialize(using = DateTimeSerializer.class)
    private LocalDateTime start;
    @JsonSerialize(using = DateTimeSerializer.class)
    private LocalDateTime end;
    private Boolean allDay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }
}



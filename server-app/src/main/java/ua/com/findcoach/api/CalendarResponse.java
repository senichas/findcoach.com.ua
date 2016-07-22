package ua.com.findcoach.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ua.com.findcoach.api.serializers.CalendarDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class CalendarResponse {
    @JsonSerialize(using = CalendarDateTimeSerializer.class)
    private LocalDateTime start;
    @JsonSerialize(using = CalendarDateTimeSerializer.class)
    private LocalDateTime end;
    private String error;
    private Boolean issort;
    private List<CalendarEvent> events;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getIssort() {
        return issort;
    }

    public void setIssort(Boolean issort) {
        this.issort = issort;
    }

    public List<CalendarEvent> getEvents() {
        return events;
    }

    public void setEvents(List<CalendarEvent> events) {
        this.events = events;
    }
}

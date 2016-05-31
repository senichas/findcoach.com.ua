package ua.com.findcoach.api;

import java.util.List;

public class CycleDto {
    private Integer cycleId;
    private String name;
    private Long startDate;
    private Long endDate;
    private String notes;

    private List<EventDto> events;

    public Integer getCycleId() {
        return cycleId;
    }

    public void setCycleId(Integer cycleId) {
        this.cycleId = cycleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<EventDto> getEvents() {
        return events;
    }

    public void setEvents(List<EventDto> events) {
        this.events = events;
    }
}

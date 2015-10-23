package ua.com.findcoach.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

/**
 *             0: 81024                id
 *             1: "team meeting"       subject
 *             2: "10/24/2015 21:06"   startt
 *             3: "12/31/1969 18:40"   end
 *             4: 1                    allday
 *             5: 0                    more than one day event Instance type
 *             6: 0                    Recurring event
 *             7: -1                   Color
 *             8: 1                    Editable
 *             9: null                 Location
 *             10: ""                  Attend
 *
 * */


@JsonSerialize(using = CalendarEventSerializer.class)
public class CalendarEvent {
    private Integer id;
    private String subject;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean allDayEvent;
    private Integer instanceType;
    private Integer recurringEvent;
    private Integer color;
    private Integer editable;
    private String location;
    private String attends;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Boolean getAllDayEvent() {
        return allDayEvent;
    }

    public void setAllDayEvent(Boolean allDayEvent) {
        this.allDayEvent = allDayEvent;
    }

    public Integer getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(Integer instanceType) {
        this.instanceType = instanceType;
    }

    public Integer getRecurringEvent() {
        return recurringEvent;
    }

    public void setRecurringEvent(Integer recurringEvent) {
        this.recurringEvent = recurringEvent;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getEditable() {
        return editable;
    }

    public void setEditable(Integer editable) {
        this.editable = editable;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttends() {
        return attends;
    }

    public void setAttends(String attends) {
        this.attends = attends;
    }
}

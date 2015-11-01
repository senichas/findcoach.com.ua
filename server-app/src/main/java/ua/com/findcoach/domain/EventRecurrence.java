package ua.com.findcoach.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_recurrence")
public class EventRecurrence {
    @Id
    @Column(name = "event_recurrence_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_recurrence_seq_gen")
    @SequenceGenerator(name = "event_recurrence_seq_gen", sequenceName = "event_recurrence_event_recurrence_id_seq")
    private Integer eventRecurrenceId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "all_day")
    private Boolean allDay;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    public Integer getEventRecurrenceId() {
        return eventRecurrenceId;
    }

    public void setEventRecurrenceId(Integer eventRecurrenceId) {
        this.eventRecurrenceId = eventRecurrenceId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}

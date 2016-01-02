package ua.com.findcoach.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "event_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_event_seq_gen")
    @SequenceGenerator(name = "event_event_seq_gen", sequenceName = "event_event_id_seq")
    private Integer eventId;

    @Column(name = "title", length = 60)
    private String title;

    @Column(name = "description", length = 140)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EventType type;

    @Column(name = "location", length = 60)
    private String location;

    @Fetch(FetchMode.SELECT)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventRecurrence> recurrences;

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

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<EventRecurrence> getRecurrences() {
        return recurrences;
    }

    public void setRecurrences(List<EventRecurrence> recurrences) {
        this.recurrences = recurrences;
    }

}

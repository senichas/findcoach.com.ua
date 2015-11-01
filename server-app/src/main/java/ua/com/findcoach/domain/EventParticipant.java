package ua.com.findcoach.domain;

import javax.persistence.*;

@Entity
@Table(name = "event_participant")
public class EventParticipant {
    @Id
    @Column(name = "event_participant_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_participant_seq_gen")
    @SequenceGenerator(name = "event_participant_seq_gen", sequenceName = "event_participant_event_participant_id_seq")
    private Integer eventParticipantId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "participant_role")
    @Enumerated(EnumType.STRING)
    private EventParticipantRole role;

    public Integer getEventParticipantId() {
        return eventParticipantId;
    }

    public void setEventParticipantId(Integer eventParticipantId) {
        this.eventParticipantId = eventParticipantId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EventParticipantRole getRole() {
        return role;
    }

    public void setRole(EventParticipantRole role) {
        this.role = role;
    }
}

package ua.com.findcoach.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "event_coach_participant")
public class EventCoachParticipant {
    @Id
    @Column(name = "event_coach_participant_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_coach_participant_seq_gen")
    @SequenceGenerator(name = "event_coach_participant_seq_gen", sequenceName = "event_coach_participant_event_coach_participant_id_seq")
    private Integer eventCoachParticipantId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @Column(name = "participant_role")
    @Enumerated(EnumType.STRING)
    private EventCoachRole role;

    public Integer getEventCoachParticipantId() {
        return eventCoachParticipantId;
    }

    public void setEventCoachParticipantId(Integer eventCoachParticipantId) {
        this.eventCoachParticipantId = eventCoachParticipantId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public EventCoachRole getRole() {
        return role;
    }

    public void setRole(EventCoachRole role) {
        this.role = role;
    }
}

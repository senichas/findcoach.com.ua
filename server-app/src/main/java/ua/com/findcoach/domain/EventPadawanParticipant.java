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
@Table(name = "event_padawan_participant")
public class EventPadawanParticipant {
    @Id
    @Column(name = "event_padawan_participant_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_padawan_participant_seq_gen")
    @SequenceGenerator(name = "event_padawan_participant_seq_gen", sequenceName = "event_padawan_participant_event_padawan_participant_id_seq")
    private Integer eventPadawanParticipantId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "padawan_id")
    private Padawan padawan;

    @Column(name = "participant_role")
    @Enumerated(EnumType.STRING)
    private EventPadawanRole role;
}

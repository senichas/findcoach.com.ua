package ua.com.findcoach.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cycle")
public class Cycle {
    @Id
    @Column(name = "cycle_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cycle_seq_gen")
    @SequenceGenerator(name = "cycle_seq_gen", sequenceName = "cycle_cycle_id_seq", allocationSize = 1)
    private Integer cycleId;

    @Column(length = 100)
    private String name;

    @Column(length = 500)
    private String notes;

    @Fetch(FetchMode.SELECT)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "cycle_event_link",
            joinColumns = @JoinColumn(name = "cycle_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}

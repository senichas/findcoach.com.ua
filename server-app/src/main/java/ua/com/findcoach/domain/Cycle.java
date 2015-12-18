package ua.com.findcoach.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cycle")
public class Cycle {
    @Id
    @Column(name = "cycle_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cycle_seq_gen")
    @SequenceGenerator(name = "cycle_seq_gen", sequenceName = "cycle_cycle_id_seq")
    private Integer cycleId;

    @Column(length = 100)
    private String name;

    @Column(length = 500)
    private String notes;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

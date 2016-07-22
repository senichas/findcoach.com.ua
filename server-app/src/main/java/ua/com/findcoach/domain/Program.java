package ua.com.findcoach.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "program")
public class Program {
    @Id
    @Column(name = "program_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "program_seq_gen")
    @SequenceGenerator(name = "program_seq_gen", sequenceName = "program_program_id_seq", allocationSize = 1)
    private Integer programId;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "padawan_id")
    private Padawan padawan;

    @Column(length = 200)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "goal")
    private Goal goal;

    @Fetch(FetchMode.SELECT)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "program_cycle_link",
            joinColumns = @JoinColumn(name = "program_id"),
            inverseJoinColumns = @JoinColumn(name = "cycle_id")
    )
    @OrderBy(clause = "cycleId ASC")
    private List<Cycle> cycles;

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Padawan getPadawan() {
        return padawan;
    }

    public void setPadawan(Padawan padawan) {
        this.padawan = padawan;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal programGoal) {
        this.goal = programGoal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cycle> getCycles() {
        return cycles;
    }

    public void setCycles(List<Cycle> cycles) {
        this.cycles = cycles;
    }
}

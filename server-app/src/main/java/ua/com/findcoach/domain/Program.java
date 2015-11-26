package ua.com.findcoach.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by DENIS on 16.11.2015.
 */

@Entity
@Table(name = "program")
public class Program {
    @Id
    @Column(name = "program_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "program_seq_gen")
    @SequenceGenerator(name = "program_seq_gen", sequenceName = "program_program_id_seq")
    private Integer programId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "padawan_id")
    private Padawan padawan;

    @Column(length = 200)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "goal")
    private Goal programGoal;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coachId) {
        this.coach = coachId;
    }

    public Padawan getPadawan() {
        return padawan;
    }

    public void setPadawan(Padawan padawanId) {
        this.padawan = padawanId;
    }

    public Goal getProgramGoal() {
        return programGoal;
    }

    public void setProgramGoal(Goal programGoal) {
        this.programGoal = programGoal;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date programStartDate) {
        this.startDate = programStartDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date programEndDate) {
        this.endDate = programEndDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

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

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "padawan_id")
    private Padawan padawan;

    @Column(name = "goal")
    private String programGoal;

    @Column(name = "start_date")
    private Date programStartDate;

    @Column(name = "end_date")
    private Date programEndDate;

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

    public String getProgramGoal() {
        return programGoal;
    }

    public void setProgramGoal(String programGoal) {
        this.programGoal = programGoal;
    }

    public Date getProgramStartDate() {
        return programStartDate;
    }

    public void setProgramStartDate(Date programStartDate) {
        this.programStartDate = programStartDate;
    }

    public Date getProgramEndDate() {
        return programEndDate;
    }

    public void setProgramEndDate(Date programEndDate) {
        this.programEndDate = programEndDate;
    }
}

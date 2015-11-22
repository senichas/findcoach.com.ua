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
    private Coach coachId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "padawan_id")
    private Padawan padawanId;

    @Column(name = "goal")
    private String programGoal;

    @Column(name = "start_date")
    private Date programStartDate;

    @Column(name = "end_date")
    private Date programEndDate;

}

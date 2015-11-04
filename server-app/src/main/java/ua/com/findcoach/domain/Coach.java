package ua.com.findcoach.domain;

import javax.persistence.*;

@Entity
@Table(name = "coach")
public class Coach implements User {

    @Id
    @Column(name = "coach_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coach_seq_gen")
    @SequenceGenerator(name = "coach_seq_gen", sequenceName = "coach_coach_id_seq")
    private Integer coachId;

    @Column(name = "first_name")
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private boolean isActive;

    @Column
    private String alias;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CoachStatus status;

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public CoachStatus getStatus() {
        return status;
    }

    public void setStatus(CoachStatus status) {
        this.status = status;
    }
}

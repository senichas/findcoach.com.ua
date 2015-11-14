package ua.com.findcoach.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

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
    @Length(max = 60)
    @Pattern(regexp = "[a-zA-Z0-9._-]")
    private String alias;

    @Column
    @Length(max = 60)
    private String title;

    @Column
    @Length(max = 140)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CoachStatus status;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public Coach(String alias, String title, String description) {
        this.alias = alias;
        this.title = title;
        this.description = description;
    }

    public Coach() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}

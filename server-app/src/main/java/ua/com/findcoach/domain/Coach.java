package ua.com.findcoach.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "coach")
public class Coach implements User {

    @Id
    @Column(name = "coach_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coach_seq_gen")
    @SequenceGenerator(name = "coach_seq_gen", sequenceName = "coach_coach_id_seq", allocationSize = 1)
    private Integer coachId;

    @Column(name = "first_name")
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private boolean active;

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
    @Column
    private Gender gender;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.MERGE)
    private List<Program> programList;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.MERGE)
    private List<Padawan> padawans;

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
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
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Program> getProgramList() {
        return programList;
    }

    public void setProgramList(List<Program> programList) {
        this.programList = programList;
    }

    public List<Padawan> getPadawans() {
        return padawans;
    }

    public void setPadawans(List<Padawan> padawans) {
        this.padawans = padawans;
    }
}

package ua.com.findcoach.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "padawan")
public class Padawan implements User {

    @Id
    @Column(name = "padawan_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "padawan_seq_gen")
    @SequenceGenerator(allocationSize = 1, initialValue = 1, name = "padawan_seq_gen", sequenceName = "padawan_padawan_id_seq")
    private Integer padawanId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    @Column
    private Date birthday;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "created_by_coach_id")
    private Coach createdBy;

    @Column
    private String notes;

    @OneToMany(mappedBy = "padawan", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Measure> measures;

    @OneToMany(mappedBy = "padawan", cascade = CascadeType.MERGE)
    private List<Program> programList;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Coach getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Coach createdBy) {
        this.createdBy = createdBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String getAlias() {
        return null;
    }

    @Override
    public void setAlias(String alias) {

    }

    public List<Measure> getMeasures() {
        return measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public Integer getPadawanId() {
        return padawanId;
    }

    public void setPadawanId(Integer padawanId) {
        this.padawanId = padawanId;
    }

    public List<Program> getProgramList() {
        return programList;
    }

    public void setProgramList(List<Program> programList) {
        this.programList = programList;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthbay) {
        this.birthday = birthbay;
    }
}

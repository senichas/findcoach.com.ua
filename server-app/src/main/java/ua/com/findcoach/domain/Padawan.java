package ua.com.findcoach.domain;

import javax.persistence.*;

@Entity
@Table(name = "padawan")
public class Padawan implements User {

    @Id
    @Column(name = "padawan_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "padawan_seq_gen")
    @SequenceGenerator(name = "padawan_seq_gen", sequenceName = "padawan_padawan_id_seq")
    private Integer coachId;

    @Column
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
    @Column
    private Sex sex;


/*    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false)
    private PadawanStatus status;*/

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

    @Override
    public Sex getSex() {
        return sex;
    }

    @Override
    public void setSex(Sex sex) {
        this.sex = sex;
    }
}

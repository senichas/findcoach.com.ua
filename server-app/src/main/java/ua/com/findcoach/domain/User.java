package ua.com.findcoach.domain;

import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;

/**
 * Created by DENIS on 20.09.2015.
 */
@Entity
@DiscriminatorFormula("(is_padawan * 10 + is_coach)")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "\"user\"")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_user_id_seq")
    private Integer userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "is_padawan", columnDefinition = "DEFAULT 0")
    private Integer isPadawan;
    @Column(name = "is_coach", columnDefinition = "DEFAULT 0")
    private Integer isCoach;
    @Column(name = "is_active", columnDefinition = "DEFAULT false", nullable = false)
    private boolean isActive;

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsPadawan() {
        return isPadawan;
    }

    public void setIsPadawan(Integer isPadawan) {
        this.isPadawan = isPadawan;
    }

    public Integer getIsCoach() {
        return isCoach;
    }

    public void setIsCoach(Integer isCoach) {
        this.isCoach = isCoach;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }


}

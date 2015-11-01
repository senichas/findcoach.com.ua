package ua.com.findcoach.domain;


import javax.persistence.*;

/**
 * Created by DENIS on 20.09.2015.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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

    @Column(name = "alias", nullable = false, unique = true)
    private String alias;

    @Column(name = "is_padawan", columnDefinition = "DEFAULT 0")
    private Integer isPadawan;
    @Column(name = "is_coach", columnDefinition = "DEFAULT 0")
    private Integer isCoach;
    @Column(name = "is_active", nullable = false)
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}

package ua.com.findcoach.domain;

import javax.persistence.*;

/**
 * Created by DENIS on 20.09.2015.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_user_id_seq")
    private int userId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "is_padawan",columnDefinition="DEFAULT false", nullable = false)
    private boolean isPadawan;
    @Column(name = "is_coach",columnDefinition="DEFAULT false", nullable = false)
    private boolean isCoach;
    @Column(name = "is_active",columnDefinition="DEFAULT false", nullable = false)
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

    public boolean isPadawan() {
        return isPadawan;
    }

    public void setPadawan(boolean isPadawan) {
        this.isPadawan = isPadawan;
    }

    public boolean isCoach() {
        return isCoach;
    }

    public void setCoach(boolean isCoach) {
        this.isCoach = isCoach;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public static void main(String[] args){
        System.out.println(Integer.MAX_VALUE);
    }


}

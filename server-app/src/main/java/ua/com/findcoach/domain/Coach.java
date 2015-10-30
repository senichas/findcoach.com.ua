package ua.com.findcoach.domain;

import javax.persistence.*;

@Entity
@Table(name = "coach")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "user_id")
public class Coach extends User {

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CoachStatus status;
    @Column(name = "alias")
    private String alias;
    @Column(name = "header")
    private String header;
    @Column(name = "coach_describe")
    private String describe;

    public CoachStatus getStatus() {
        return status;
    }

    public void setStatus(CoachStatus status) {
        this.status = status;
    }
}

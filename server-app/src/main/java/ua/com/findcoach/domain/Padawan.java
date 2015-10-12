package ua.com.findcoach.domain;

import javax.persistence.*;

@Entity
@Table(name = "padawan")
public class Padawan {
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id")
    private User userId;
    @Column (name = "user_status", nullable = false)
    private PadawanStatus status;

    public Padawan (){
    }

    @Enumerated(EnumType.STRING)
    public PadawanStatus getStatus() {
        return status;
    }

    public User getId(){
        return userId;
    }

    public void setId (User userId){
        this.userId=userId;
    }
}

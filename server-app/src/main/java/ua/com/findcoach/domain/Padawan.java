package ua.com.findcoach.domain;

import javax.persistence.*;

@Entity
@Table(name = "padawan")
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "user_id")
public class Padawan extends User{

    @Enumerated(EnumType.STRING)
    @Column (name = "user_status", nullable = false)
    private PadawanStatus status;

    public PadawanStatus getStatus() {
        return status;
    }

    public void setStatus(PadawanStatus status) {
        this.status = status;
    }
}

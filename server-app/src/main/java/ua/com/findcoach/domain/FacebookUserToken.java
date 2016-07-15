package ua.com.findcoach.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "facebook_user_token")
public class FacebookUserToken {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(length = 60)
    private String email;

    @Column(name = "long_lived_token", length = 100)
    private String longLivedToken;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLongLivedToken() {
        return longLivedToken;
    }

    public void setLongLivedToken(String longLivedToken) {
        this.longLivedToken = longLivedToken;
    }
}

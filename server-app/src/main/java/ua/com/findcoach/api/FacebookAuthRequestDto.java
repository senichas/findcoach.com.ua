package ua.com.findcoach.api;

public class FacebookAuthRequestDto {
    private Long userId;
    private String email;
    private String shortLivedToken;
    private Long applicationId;

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

    public String getShortLivedToken() {
        return shortLivedToken;
    }

    public void setShortLivedToken(String shortLivedToken) {
        this.shortLivedToken = shortLivedToken;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
}

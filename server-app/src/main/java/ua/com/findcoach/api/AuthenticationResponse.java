package ua.com.findcoach.api;

public class AuthenticationResponse {
    private boolean result;
    private String message;
    private String redirectLink;


    public AuthenticationResponse(boolean result, String message, String redirectLink) {
        this.result = result;
        this.message = message;
        this.redirectLink = redirectLink;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedirectLink() {
        return redirectLink;
    }

    public void setRedirectLink(String redirectLink) {
        this.redirectLink = redirectLink;
    }
}

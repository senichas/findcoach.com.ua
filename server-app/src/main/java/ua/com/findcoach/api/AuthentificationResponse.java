package ua.com.findcoach.api;

/**
 * Created by DENIS on 24.09.2015.
 */
public class AuthentificationResponse {
    private boolean result;
    private String message;

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
}

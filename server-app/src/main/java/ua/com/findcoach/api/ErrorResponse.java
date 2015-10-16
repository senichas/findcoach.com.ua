package ua.com.findcoach.api;

import org.springframework.http.HttpStatus;

/**
 * Created by DENIS on 16.10.2015.
 */
public class ErrorResponse {
    private HttpStatus httpStatus;
    private String errorMessege;
    private String exceptionMessege;

    public ErrorResponse(HttpStatus httpStatus, String errorMessege, String exceptionMessege) {
        this.httpStatus = httpStatus;
        this.errorMessege = errorMessege;
        this.exceptionMessege = exceptionMessege;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorMessege() {
        return errorMessege;
    }

    public void setErrorMessege(String errorMessege) {
        this.errorMessege = errorMessege;
    }

    public String getExceptionMessege() {
        return exceptionMessege;
    }

    public void setExceptionMessege(String exceptionMessege) {
        this.exceptionMessege = exceptionMessege;
    }
}

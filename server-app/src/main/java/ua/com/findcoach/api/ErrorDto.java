package ua.com.findcoach.api;

/**
 * Created by DENIS on 16.10.2015.
 */
public class ErrorDto {
    private String errorMessege;
    private String exceptionMessege;

    public ErrorDto(String errorMessege, String exceptionMessege) {
        this.errorMessege = errorMessege;
        this.exceptionMessege = exceptionMessege;
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
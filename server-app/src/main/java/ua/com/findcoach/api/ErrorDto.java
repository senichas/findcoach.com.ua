package ua.com.findcoach.api;

public class ErrorDto {
    private String errorMessage;
    private String exceptionMessage;

    public ErrorDto(String errorMessage, String exceptionMessage) {
        this.errorMessage = errorMessage;
        this.exceptionMessage = exceptionMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}

package ua.com.findcoach.api;

import java.util.List;

public class RestResponse {
    private List<ErrorDto> errors;

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDto> errors) {
        this.errors = errors;
    }
}

package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.com.findcoach.api.ErrorResponse;
import ua.com.findcoach.exception.StatusUpdateException;
import ua.com.findcoach.i18n.LocalizedMessageResoler;

/**
 * Created by DENIS on 15.10.2015.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private LocalizedMessageResoler messageResoler;

    @ExceptionHandler(value = StatusUpdateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse statusUpdateExeption(Exception exception) {
        return new ErrorResponse(messageResoler.getMessage("error.status.failure_update"),exception.getMessage());
    }
}

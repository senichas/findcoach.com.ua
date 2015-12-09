package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.com.findcoach.api.ErrorDto;
import ua.com.findcoach.exception.StatusUpdateException;
import ua.com.findcoach.i18n.LocalizedMessageResolver;

/**
 * Created by DENIS on 15.10.2015.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private LocalizedMessageResolver messageResolver;

    @ExceptionHandler(value = StatusUpdateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto statusUpdateExeption(Exception exception) {
        return new ErrorDto(messageResolver.getMessage("error.status.failure_update"),exception.getMessage());
    }
}

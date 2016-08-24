package ua.com.findcoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.com.findcoach.api.ErrorDto;
import ua.com.findcoach.exception.ValidationException;
import ua.com.findcoach.i18n.LocalizedMessageResolver;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private LocalizedMessageResolver messageResolver;

    @ExceptionHandler(value = ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDto validationException(Exception exception) {
        return new ErrorDto(messageResolver.getMessage("error.validation.general_error"), exception.getMessage());
    }
}

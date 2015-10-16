package ua.com.findcoach.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ua.com.findcoach.exception.StatusUpdateException;

/**
 * Created by DENIS on 15.10.2015.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = StatusUpdateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String statusUpdateExeption(Exception exception) {
        return "{error:"+ exception.getMessage()+"}";
    }
}

package com.example.anagram.controllers.exceptions;

import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {


    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested object with provided id not found")
    public String handleObjectNotFoundException(HttpServletRequest request, Exception e) {
        log.error(e.getMessage());
        return e.getMessage();
    }
}

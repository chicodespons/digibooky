package com.switchfully.digibooky.controller;


import com.switchfully.digibooky.exceptions.*;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected void illegalArgumentException(IllegalArgumentException ex, HttpServletResponse response) throws
            IOException {
        response.sendError(HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }

    @ExceptionHandler(BookByISBNNotFoundException.class)
    protected void bookByISBNNotFoundException(BookByISBNNotFoundException ex, HttpServletResponse response) throws
            IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(InvalidEmailAddressException.class)
    protected void userAlreadyExistsException(InvalidEmailAddressException ex, HttpServletResponse response) throws
            IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(InssAlreadyExistsException.class)
    protected void inssAlreadyExistsException(InssAlreadyExistsException ex, HttpServletResponse response) throws
            IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(UserCreationException.class)
    protected void userValidationException(UserCreationException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(InvalidRoleException.class)
    protected void invalidRoleException(InvalidRoleException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}
package com.switchfully.digibooky.controller;


import com.switchfully.digibooky.controller.exceptions.BookByISBNNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected void IllegalArgumentException(IllegalArgumentException ex, HttpServletResponse response) throws
            IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(BookByISBNNotFoundException.class)
    protected void BookByISBNNotFoundException(BookByISBNNotFoundException ex, HttpServletResponse response) throws
            IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    protected void UnvalidStudyPointsException(IllegalArgumentException ex, HttpServletResponse response) throws
//            IOException {
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }

}
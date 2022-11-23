package com.switchfully.digibooky.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
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
        response.sendError(HttpStatus.FORBIDDEN.value(), ex.getMessage());
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    protected void UnvalidStudyPointsException(IllegalArgumentException ex, HttpServletResponse response) throws
//            IOException {
//        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//    }

}
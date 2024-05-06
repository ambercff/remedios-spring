package com.remedios.amber.curso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e){
        // 1. Create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionClass apiExceptionClass = new ExceptionClass(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        // 2. Return response entity
        return new ResponseEntity<>(apiExceptionClass, badRequest);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, BadCredentialsException.class})
    public ResponseEntity<Object>handleIllegalArgumentException(Exception e) {
        HttpStatus conflict = HttpStatus.CONFLICT;
        ExceptionClass apiExceptionClass = new ExceptionClass(
                e.getMessage(),
                conflict,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiExceptionClass, conflict);
    }

}
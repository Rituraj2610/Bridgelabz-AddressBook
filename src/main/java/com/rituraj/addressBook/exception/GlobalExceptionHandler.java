package com.rituraj.addressBook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<Response> handleContactNotFound(ContactNotFoundException ex) {
        Response response = new Response<>("FAILED", ex.getMessage(), null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContactAlreadyExistsException.class)
    public ResponseEntity<Response> handleContactAlreadyExists(ContactAlreadyExistsException ex) {
        Response response = new Response<>("FAILED", ex.getMessage(), null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class) // for any other unknown exceptions
    public ResponseEntity<Response> handleGlobalException(Exception ex) {
        Response response = new Response<>("FAILED", "Something went wrong: " + ex.getMessage(), null, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


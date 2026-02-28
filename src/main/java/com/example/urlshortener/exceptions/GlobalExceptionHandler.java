package com.example.urlshortener.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(URLNotFoundException.class)
    public ResponseEntity<String> handleURLNotFoundException(URLNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity.badRequest().body("URL Exists");
    }

    @ExceptionHandler(URLExpiredException.class)
    public ResponseEntity<String> handleExpiredURLException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}

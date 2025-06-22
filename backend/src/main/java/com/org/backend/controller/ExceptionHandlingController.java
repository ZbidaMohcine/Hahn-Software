package com.org.backend.controller;

import com.org.backend.exception.PersonneNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice

public class ExceptionHandlingController {
    @ExceptionHandler(PersonneNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(PersonneNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", "Not Found");
        body.put("message", ex.getMessage());
        body.put("code", "404");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}

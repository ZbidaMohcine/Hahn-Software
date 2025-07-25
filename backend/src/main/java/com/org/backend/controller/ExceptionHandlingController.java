package com.org.backend.controller;

import com.org.backend.dto.CodeError;
import com.org.backend.exception.PersonneNotFoundException;
import com.org.backend.exception.ProductNoValidNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice

public class ExceptionHandlingController {
    @ExceptionHandler(PersonneNotFoundException.class)
    public ResponseEntity<Object> handlePersonneNotFound(PersonneNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());
        body.put("codeError", ex.getCodeError());
        body.put("detail", ex.getMessage());
        body.put("code", "404");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("code", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Validation Failed");

        // Collect validation errors field by field
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage(),
                        (existing, replacement) -> existing // handle duplicate keys if needed
                ));

        body.put("details", fieldErrors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error",CodeError.SYSTEM_ERROR.getMessage() );
        body.put("codeError", CodeError.SYSTEM_ERROR.getCode());
        body.put("code", "500");

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ProductNoValidNameException.class)
    public ResponseEntity<Object> handleProductNoValidName(ProductNoValidNameException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());
        body.put("code", ex.getErrorCode());
        body.put("detail", ex.getMessage());
        body.put("path", "/api/products");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<Object> handleBadRequest(HttpClientErrorException.BadRequest ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("error", ex.getMessage());
        body.put("code", "400");
        body.put("detail", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}

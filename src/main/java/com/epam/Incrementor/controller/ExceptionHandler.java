package com.epam.Incrementor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    public Map<String, Object> handleValidationExceptions(final BindException exception) {
        final Map<String, Object> errors = new HashMap<>();
        exception.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        log.info("Validation fail");
        return Map.of("result", errors);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleValidationExceptions(final RuntimeException exception) {
        return Map.of("result", exception.getMessage());
    }

}

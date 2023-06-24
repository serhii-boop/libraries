package com.nulp.libraries.configuration;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    //other exception handlers

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            RuntimeException ex) {
        return buildResponseEntity(new ApiError(NOT_FOUND, ex.getMessage(), LocalDateTime.now()));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.excCode);
    }

    private class ApiError {
        public HttpStatus excCode;
        public String msg;
        public LocalDateTime timestamp;

        ApiError(HttpStatus excCode, String msg, LocalDateTime timestamp) {
            this.excCode = excCode;
            this.msg = msg;
            this.timestamp = timestamp;
        }
    }
}

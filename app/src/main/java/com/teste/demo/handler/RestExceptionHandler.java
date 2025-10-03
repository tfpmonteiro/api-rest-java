package com.teste.demo.handler;

import com.teste.demo.model.error.ErrorMessage;
import com.teste.demo.model.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}

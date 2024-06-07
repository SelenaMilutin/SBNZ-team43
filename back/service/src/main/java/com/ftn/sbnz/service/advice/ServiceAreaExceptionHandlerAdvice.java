package com.ftn.sbnz.service.advice;

import com.ftn.sbnz.service.exception.servicearea.NoServiceAreaAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceAreaExceptionHandlerAdvice {

    @ExceptionHandler(NoServiceAreaAvailableException.class)
    public ResponseEntity<String> handleNoServiceAreaAvailable(NoServiceAreaAvailableException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}

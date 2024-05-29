package com.ftn.sbnz.service.advice;

import com.ftn.sbnz.service.exception.user.UserDoesNotExistByIdException;
import com.ftn.sbnz.service.exception.user.UsernameAlreadyExistsException;
import com.ftn.sbnz.service.exception.user.UsernameNotFoundForLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandlerAdvice {

    @ExceptionHandler(UsernameNotFoundForLoginException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundForLoginException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<String> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserDoesNotExistByIdException.class)
    public ResponseEntity<String> handleUserDoesNotExistByIdException(UserDoesNotExistByIdException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
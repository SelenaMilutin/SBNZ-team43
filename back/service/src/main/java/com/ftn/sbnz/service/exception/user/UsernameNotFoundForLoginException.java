package com.ftn.sbnz.service.exception.user;

public class UsernameNotFoundForLoginException extends RuntimeException {

    public UsernameNotFoundForLoginException(String username) {
        super(String.format("Username %s not found for login", username));
    }

}

package com.ftn.sbnz.service.exception.user;

public class UserBlockedException extends RuntimeException {

    public UserBlockedException(Long id) {
        super("User with id " + id + " is blocked");
    }
}

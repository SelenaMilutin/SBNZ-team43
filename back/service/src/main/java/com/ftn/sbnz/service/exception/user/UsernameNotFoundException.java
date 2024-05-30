package com.ftn.sbnz.service.exception.user;

public class UsernameNotFoundException extends RuntimeException {

    public UsernameNotFoundException(String username) {
        super(String.format("Username %s not found", username));
    }

}

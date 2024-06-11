package com.ftn.sbnz.service.exception.user;

import java.util.UUID;

public class UserDoesNotExistByIdException extends  RuntimeException {

    public UserDoesNotExistByIdException(Long userId) {
        super(String.format("User with id %s does not exist", userId));
    }
}

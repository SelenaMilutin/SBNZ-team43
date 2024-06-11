package com.ftn.sbnz.model.user;

import lombok.Getter;

@Getter
public enum Role {

    CLIENT("CLIENT"),
    ADMIN("ADMIN");

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }
}

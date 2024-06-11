package com.ftn.sbnz.service.exception.contract;

public class NoDiscountExistsForClientException extends RuntimeException {

    public NoDiscountExistsForClientException(Long clientId) {
        super("No discount exists for client id= " + clientId);
    }

}

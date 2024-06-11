package com.ftn.sbnz.service.exception.packages;

public class PackageDoesNotExistByIdException extends RuntimeException {

    public PackageDoesNotExistByIdException(String id) {
        super("Package with id " + id + " does not exist");
    }

}

package com.ftn.sbnz.service.exception.servicearea;

public class ServiceAreaDoesNotExistException extends RuntimeException {

    public ServiceAreaDoesNotExistException(Long id) {
        super("Service area " + id + " does not exist");
    }

}

package com.ftn.sbnz.service.exception.servicearea;

public class NoServiceAreaAvailableException extends RuntimeException{

    public NoServiceAreaAvailableException() {
        super("No service area available");
    }
}

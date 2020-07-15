package com.example.spaceagency.exception;

public class AppUserNotFoundException extends Exception {

    public AppUserNotFoundException() {
        super();
    }

    public AppUserNotFoundException(Long id) {
        super(String.format("Application User with id %d not found", id));
    }
}

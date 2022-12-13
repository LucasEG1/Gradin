package com.lucas.Gradin.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String msg) {
        super("ERROR: Resource not found: " + msg);
    }

}

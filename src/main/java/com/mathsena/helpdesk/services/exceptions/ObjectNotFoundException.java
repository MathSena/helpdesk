package com.mathsena.helpdesk.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    private static final Long serialVersionUID = 1L;


    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}

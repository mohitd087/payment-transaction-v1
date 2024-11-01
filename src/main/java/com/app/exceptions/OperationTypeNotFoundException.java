package com.app.exceptions;

public class OperationTypeNotFoundException extends RuntimeException {
    public OperationTypeNotFoundException(String message) {
        super(message);
    }
}

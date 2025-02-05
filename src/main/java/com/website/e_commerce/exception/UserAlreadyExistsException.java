package com.website.e_commerce.exception;

public class UserAlreadyExistsException extends RuntimeException {

    // Constructor that accepts a message
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    // Constructor that accepts a message and a cause
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

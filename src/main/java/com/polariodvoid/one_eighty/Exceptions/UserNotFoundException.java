package com.polariodvoid.one_eighty.Exceptions;

public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }
}


package com.inventory.exceptions;

public class UnAuthorizedException extends Exception {
    public UnAuthorizedException(String message) {
        super(message);
    }
}

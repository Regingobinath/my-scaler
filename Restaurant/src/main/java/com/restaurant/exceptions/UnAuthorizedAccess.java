package com.restaurant.exceptions;

public class UnAuthorizedAccess extends Exception{
    public UnAuthorizedAccess(String message) {
        super(message);
    }
}

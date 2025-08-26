package com.learn.parkinglot.exceptions;

public class TicketNotFoundException extends Exception{
    public TicketNotFoundException(String message) {
        super(message);
    }
}

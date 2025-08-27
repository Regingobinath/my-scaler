package com.myscaler.bms.exeptions;

public class SeatsNotAvailable extends Exception{
    public SeatsNotAvailable(String message) {
        super(message);
    }
}

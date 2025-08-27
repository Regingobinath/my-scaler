package com.myscaler.bms.exeptions;


public class UnAuthorizedAccessException extends Exception{
    public UnAuthorizedAccessException(String message) {
        super(message);
    }

}

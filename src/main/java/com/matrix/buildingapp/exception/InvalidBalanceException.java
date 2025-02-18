package com.matrix.buildingapp.exception;

public class InvalidBalanceException extends RuntimeException{
    public  InvalidBalanceException(String message){
        super (message);
    }
}

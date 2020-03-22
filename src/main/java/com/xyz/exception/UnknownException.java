package com.xyz.exception;

public class UnknownException extends BaseException{
    public UnknownException(String message) {
        this.setCode(50000);
        this.setMessage(message);
    }
}

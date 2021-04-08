package com.xyz.exception;

public class BaseException extends RuntimeException {
    private Integer code;
    private String message;

    public BaseException(){}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

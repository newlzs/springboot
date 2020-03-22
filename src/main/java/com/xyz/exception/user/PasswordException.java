package com.xyz.exception.user;

import com.xyz.exception.BaseException;

public class PasswordException extends BaseException {
    public PasswordException() {
        this.setCode(10010);
        this.setMessage("密码错误");
    }
}

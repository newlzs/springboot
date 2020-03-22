package com.xyz.exception.user;

import com.xyz.exception.BaseException;

public class NeedLoginException extends BaseException {
    public NeedLoginException() {
        this.setCode(20010);
        this.setMessage("请先登录");
    }
}

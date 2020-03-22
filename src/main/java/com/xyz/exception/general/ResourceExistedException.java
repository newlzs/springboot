package com.xyz.exception.general;

import com.xyz.exception.BaseException;

public class ResourceExistedException extends BaseException {
    public ResourceExistedException(String resource) {
        this.setCode(10002);
        this.setMessage(resource + "已存在");
    }
}

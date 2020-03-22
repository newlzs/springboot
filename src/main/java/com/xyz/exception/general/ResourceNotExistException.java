package com.xyz.exception.general;

import com.xyz.exception.BaseException;

public class ResourceNotExistException extends BaseException {
    private String resourceName;

    public ResourceNotExistException(String resourceName) {
        this.setCode(10001);
        this.resourceName = resourceName;
        this.setMessage(resourceName + "不存在");
    }
}

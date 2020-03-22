package com.xyz.exception.general;

import com.xyz.exception.BaseException;

public class PermissionDeniedException extends BaseException {
    public PermissionDeniedException() {
        this.setCode(10004);
        this.setMessage("没有权限");
    }
}

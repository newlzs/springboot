package com.xyz.common;
/**
* @author lzs
* @Date 2020/3/20
*/

public class Response<T> {
    private int code;
    private Object data;

    public Response(int code, Object data) {
        this.data = data;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

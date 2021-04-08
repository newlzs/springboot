package com.xyz.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.xyz.exception.general.FormValidatorException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lzs
 * @date 2021/2/17 10:59
 * @description
 */
//全局异常捕捉处理
@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 400);
        //判断异常的类型,返回不一样的返回值
        if(ex instanceof MissingServletRequestParameterException){
            map.put("msg","缺少必需参数："+((MissingServletRequestParameterException) ex).getParameterName());
        }
        else if(ex instanceof BaseException){
            map.put("msg",ex.getMessage());
        }
        return map;
    }
}

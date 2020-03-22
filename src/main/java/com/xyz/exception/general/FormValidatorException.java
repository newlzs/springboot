package com.xyz.exception.general;

import com.xyz.exception.BaseException;
import org.springframework.validation.BindingResult;

import javax.naming.Binding;

public class FormValidatorException extends BaseException {
    public FormValidatorException(BindingResult bindingResult) {
        this.setMessage(bindingResult.getFieldError().getField() +
                bindingResult.getFieldError().getDefaultMessage());
        this.setCode(10003);
    }
    public FormValidatorException(String message) {
        this.setMessage(message);
        this.setCode(10003);
    }
}

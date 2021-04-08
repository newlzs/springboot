package com.xyz.dto.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserLoginRequest {
    @ApiModelProperty(value = "用户名",required = true,example = "root")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "密码",required = true,example = "123456")
    @NotBlank
    private String password;

    @NotNull
    @Max(2)
    @Min(1)
    private Integer client;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }
}

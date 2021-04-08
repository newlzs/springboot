package com.xyz.dto.request;

import com.xyz.enums.UserSexEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(value = "用户注册表单对象", description = "用户注册表单对象")
public class UserRegisterRequest {
    @ApiModelProperty(value = "用户名",required = true,example = "root")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "密码",required = true,example = "123456")
    @NotBlank
    private String password;

    private UserSexEnum sex;
    private String nickName;

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

    public UserSexEnum getSex() {
        return sex;
    }

    public void setSex(UserSexEnum sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}

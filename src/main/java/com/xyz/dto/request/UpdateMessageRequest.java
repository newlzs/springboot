package com.xyz.dto.request;

import com.xyz.enums.UserSexEnum;

public class UpdateMessageRequest {
    private String name;
    private String nickName;
    private UserSexEnum sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public UserSexEnum getSex() {
        return sex;
    }

    public void setSex(UserSexEnum sex) {
        this.sex = sex;
    }
}

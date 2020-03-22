package com.xyz.dto.response;

import com.xyz.enums.UserSexEnum;
import com.xyz.pojo.User;
/**
* @author lzs
* @Date 2020/3/21
*/

public class UserLoginResponse {
    private String token;
    private User user;

    public UserLoginResponse(String token, com.xyz.pojo.User  user) {
        this.token = token;
        User u = new User();
        u.setUserName(user.getUserName());
        u.setUserSex(user.getUserSex());
        u.setNickName(user.getNickName());
        this.user = u;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    class User {
        private String UserName;
        private String NickName;
        private UserSexEnum UserSex;


        public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getNickName() {
            return NickName;
        }

        public void setNickName(String nickName) {
            NickName = nickName;
        }

        public UserSexEnum getUserSex() {
            return UserSex;
        }

        public void setUserSex(UserSexEnum userSex) {
            UserSex = userSex;
        }
    }
}

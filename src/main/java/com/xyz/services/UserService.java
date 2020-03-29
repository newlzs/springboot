package com.xyz.services;

import com.xyz.dto.response.UserLoginResponse;
import com.xyz.exception.BaseException;
import com.xyz.exception.general.ResourceExistedException;
import com.xyz.exception.general.ResourceNotExistException;
import com.xyz.exception.user.PasswordException;
import com.xyz.mapper.UserMapper;
import com.xyz.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenService tokenService;

    public UserLoginResponse login(String name, String password, int client, String ip) throws BaseException {
        User user = userMapper.getUserByName(name);
        if(user == null) {
            throw new ResourceNotExistException("用户");
        }else {
            if(!password.equals(user.getPassWord())) {
                throw new PasswordException();
            }
        }
        String token = tokenService.createToken(user.getId(), client, ip);
        return new UserLoginResponse(token, user);
    }

    public void register(User user) throws BaseException {
        User u = userMapper.getUserByName(user.getUserName());
        if(u != null) {
            throw new ResourceExistedException("用户名");
        }
        userMapper.createUser(user);
    }

    public User getUserById(long userId) {
        return userMapper.getUserById(userId);
    }
    public boolean updateUser(User user) {
        return userMapper.updateUser(user) == 1;
    }
    public boolean deleteUser(User user) {
        return userMapper.deleteUser(user.getId()) == 1;
    }
}

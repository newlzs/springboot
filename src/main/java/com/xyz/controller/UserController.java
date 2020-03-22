package com.xyz.controller;

import com.xyz.common.Response;
import com.xyz.dto.request.UpdateMessageRequest;
import com.xyz.dto.request.UpdatePasswordRequest;
import com.xyz.dto.request.UserLoginRequest;
import com.xyz.dto.request.UserRegisterRequest;
import com.xyz.dto.response.UserLoginResponse;
import com.xyz.exception.BaseException;
import com.xyz.exception.UnknownException;
import com.xyz.exception.general.FormValidatorException;
import com.xyz.exception.user.PasswordException;
import com.xyz.pojo.User;
import com.xyz.mapper.UserMapper;
import com.xyz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Response register(@RequestBody @Valid UserRegisterRequest registerRequest,
                             BindingResult bindingResult) throws BaseException {
        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }
        User user = new User();
        user.setPassWord(registerRequest.getPassword());
        user.setUserName(registerRequest.getName());
        user.setNickName(registerRequest.getNickName());
        user.setUserSex(registerRequest.getSex());

        userService.register(user);
        return new Response(0, user.getId());
    }

    @PostMapping("/login")
    public Response loginRequest(HttpServletRequest request, @RequestBody @Valid UserLoginRequest loginRequest,
                                 BindingResult bindingResult) throws BaseException {
        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }
        UserLoginResponse response = userService.login(loginRequest.getName(),
                loginRequest.getPassword(), loginRequest.getClient(), request.getRemoteHost());

        return new Response(0, response);
    }

    @GetMapping("/me")
    public Response getMine(HttpServletRequest request) {
        User user = (User)request.getAttribute("user");
        return new Response(0, user);
    }

    @PostMapping("/password")
    public Response ChangeMessage(@RequestBody @Valid UpdatePasswordRequest passwordRequest,
                                  BindingResult bindingResult, HttpServletRequest request) throws BaseException {
        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }
        User user = (User) request.getAttribute("user");
        String oldPassword = passwordRequest.getOldPassword();
        String newPassword = passwordRequest.getNewPassword();
        if(!oldPassword.equals(user.getPassWord())){
            throw new PasswordException();
        }
        user.setPassWord(newPassword);
        if(!userService.updateUser(user)) {
            throw new UnknownException("密码修改失败");
        }
        return new Response(0, null);
    }

    @PostMapping("/message")
    public Response nickName(@RequestBody @Valid UpdateMessageRequest messageRequest,
                             HttpServletRequest request) throws BaseException {
        User user = (User) request.getAttribute("user");
        user.setUserSex(messageRequest.getSex());
        user.setNickName(messageRequest.getNickName());
        user.setUserName(messageRequest.getName());
        if(!userService.updateUser(user)) {
            throw new UnknownException("修改信息失败");
        }
        return new Response(0, null);
    }
}
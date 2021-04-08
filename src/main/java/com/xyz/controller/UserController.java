package com.xyz.controller;

import com.xyz.common.Response;
import com.xyz.common.SnowFlakeIdWorker;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(value = "用户信息管理")
public class UserController {
    @Value("${uuid.workerId}")
    private long workerId;
    @Value("${uuid.dataCenterId}")
    private long dataCenterId;

    private SnowFlakeIdWorker snowFlakeId = new SnowFlakeIdWorker(workerId, dataCenterId);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public Response register(@RequestBody @Valid UserRegisterRequest registerRequest,
                             BindingResult bindingResult) throws BaseException {
        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }
        User user = new User();
        user.setId(snowFlakeId.nextId());
        user.setPassWord(registerRequest.getPassword());
        user.setUserName(registerRequest.getName());
        user.setNickName(registerRequest.getNickName());
        user.setUserSex(registerRequest.getSex());

        userService.register(user);
        return new Response(0, user.getId());
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登陆")
    public Response loginRequest(HttpServletRequest request, @RequestBody @Valid UserLoginRequest loginRequest,
                                 BindingResult bindingResult) throws BaseException {
        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }
        UserLoginResponse response = userService.login(loginRequest.getName(),
                loginRequest.getPassword(), loginRequest.getClient(), request.getRemoteHost());

        return new Response(0, response);
    }

    @ApiOperation(value = "获取个人信息")
    @GetMapping("/me")
    public Response getMine(HttpServletRequest request) {
        User user = (User)request.getAttribute("user");
        return new Response(0, user);
    }

    @ApiOperation(value = "修改密码")
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

    @ApiOperation(value = "修改个人信息")
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
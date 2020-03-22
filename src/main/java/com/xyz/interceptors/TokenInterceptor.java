package com.xyz.interceptors;

import com.xyz.exception.BaseException;
import com.xyz.exception.user.NeedLoginException;
import com.xyz.pojo.User;
import com.xyz.services.TokenService;
import com.xyz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserService userService;

    private ArrayList<String> unCheckUrlsRegex = new ArrayList<String>() {{
        add("/post/.*");
    }};

    private ArrayList<String> unCheckUrlNormal = new ArrayList<String>() {{
        add("/user/register");
        add("/user/login");
        add("/error");
    }};

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object o) throws BaseException {
        String token = request.getHeader("token");
        String url = request.getRequestURI();

        if(request.getMethod().equals("OPTIONS")) {
            return true;
        }

        for(String u: unCheckUrlsRegex) {
            Pattern pattern = Pattern.compile(u);
            Matcher matcher = pattern.matcher(url);
            if(matcher.matches()) {
                return true;
            }
        }

        for(String u: unCheckUrlNormal) {
            if(url.equals(u)) {
                return true;
            }
        }

        long userId = tokenService.checkToken(token);
        if(userId == -1) {
            throw new NeedLoginException();
        }

        User user = userService.getUserById(userId);
        request.setAttribute("user", user);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
    }
}

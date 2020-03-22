package com.xyz.services;

import com.xyz.pojo.User;
import com.xyz.enums.UserSexEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@Service
@RestController
public class HelloService {
    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @RequestMapping("/1")
    public void testString() {
        stringRedisTemplate.opsForValue().set("strKey", "zwqh");
        System.out.println(stringRedisTemplate.opsForValue().get("strKey"));
    }

    @RequestMapping("/2")
    public void testSerializable() {
        User user=new User();
        user.setId(1L);
        user.setUserName("朝雾轻寒");
        user.setUserSex(UserSexEnum.MAN);
        serializableRedisTemplate.opsForValue().set("user", user);
        User user2 = (User) serializableRedisTemplate.opsForValue().get("user");
        System.out.println("user:"+user2.getId()+","+user2.getUserName()+","+user2.getUserSex());
    }
}


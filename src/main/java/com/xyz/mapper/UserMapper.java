package com.xyz.mapper;

import com.xyz.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> getAll();

    User getUserById(Long id);
    User getUserByName(String name);

    void createUser(User user);

    int updateUser(User user);

    int deleteUser(Long id);
}

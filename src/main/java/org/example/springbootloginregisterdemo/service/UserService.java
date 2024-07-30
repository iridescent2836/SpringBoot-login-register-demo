package org.example.springbootloginregisterdemo.service;

import org.example.springbootloginregisterdemo.entity.User;
import org.example.springbootloginregisterdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper  userMapper;

    public int register(User user) {
        return userMapper.insert(user);
    }


}

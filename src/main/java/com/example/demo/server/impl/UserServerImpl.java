package com.example.demo.server.impl;

import com.example.demo.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserServer")
public class UserServerImpl implements UserServer {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }
}

package com.example.demo.server;

import com.example.demo.entity.User;

import java.util.List;

public interface UserServer {

    List<User> userList();

    String userName(int id);
}

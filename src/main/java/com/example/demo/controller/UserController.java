package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServer userServer;

    @PostMapping("/userList")
    public void userList() {
        List<User> users = userServer.userList();
        for (Object o : users) {
            System.out.println(o);
        }
    }

    @GetMapping("/userDetail")
    public void userDetail() {
        String name = userServer.userName(1);
        System.out.println(name);
        System.out.println("I can write code!");
    }

}

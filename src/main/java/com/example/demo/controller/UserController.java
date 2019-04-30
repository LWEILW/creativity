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


    @GetMapping("/userDetail")
    public void userDetail() {
        User user = userServer.getUserById(1);
        System.out.println(user);
        System.out.println("I can write code!");
    }

}

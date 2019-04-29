package com.example.demo.server.impl;

import com.example.demo.entity.User;
import com.example.demo.server.UserServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServerImplTest {
    @Autowired
    private UserServer userServer;

    @Test
    public void userList() {
        List<User> users = userServer.userList();
        for (Object o : users)
            System.out.println(o);
    }
}
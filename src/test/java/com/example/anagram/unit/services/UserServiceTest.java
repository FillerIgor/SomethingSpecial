package com.example.anagram.unit.services;

import com.example.anagram.AbstractJunitTestClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

public class UserServiceTest extends AbstractJunitTestClass {

    @Autowired
    private UserService userService;

    @Test
    public void saveOldStyle() throws Exception {
    }

    @Test
    public void getOldStyle() throws Exception {

    }
}
package com.example.anagram.controllers;

import com.example.anagram.models.User;
import com.example.anagram.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUser(@RequestParam String id) throws ObjectNotFoundException, IOException {
        return userService.findOne(id);
    }

    @PostMapping
    public void createUser(@RequestBody User user) throws JsonProcessingException {
        userService.saveOldStyle(user);
    }

}

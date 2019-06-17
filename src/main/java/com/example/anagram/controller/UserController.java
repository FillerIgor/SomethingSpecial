package com.example.anagram.controller;

import com.example.anagram.model.User;
import com.example.anagram.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{uuid}")
    public User getUser(@PathVariable String uuid) {
        return userService.findOne(uuid);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public String createUser(@RequestBody JsonNode user) {
        System.out.println("PAUSE");
//        return userService.saveUser(user);
        return "";
    }

    @PutMapping
    public String updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping(value = "/{uuid}")
    public String deleteUser(@PathVariable String uuid) {
        return userService.deleteUserById(uuid);
    }

    @DeleteMapping("/all")
    public String deleteAllUsers() {
        userService.deleteAllUsers();
        return "All users successfully deleted !";
    }

}

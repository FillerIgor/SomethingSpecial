package com.example.anagram.controllers;

import com.example.anagram.models.User;
import com.example.anagram.unit.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) throws JsonProcessingException {
        userService.saveOldStyle(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) throws ObjectNotFoundException, IOException {
        User user = userService.findOne(id);
        user.add(linkTo(UserController.class).slash(id).withSelfRel());
        return user;
    }
}

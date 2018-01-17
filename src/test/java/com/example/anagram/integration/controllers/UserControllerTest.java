package com.example.anagram.integration.controllers;

import com.example.anagram.AbstractMockMvc;
import com.example.anagram.models.User;
import com.example.anagram.unit.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.tools.rmi.ObjectNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends AbstractMockMvc {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @SpyBean
    UserService userService;

    @Test
    public void createUser() throws Exception {
        //given
        User user = new User();
        doNothing().when(userService).saveOldStyle(user);
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .content(objectMapper.writeValueAsBytes(user))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated());
        //then
        verify(userService, times(1)).saveOldStyle(any(User.class));
    }

    @Test
    public void should_throw_exception_when_user_not_found() throws Exception {
        //given
        String randomUserId = UUID.randomUUID().toString();
        doThrow(ObjectNotFoundException.class).when(userService).findOne(randomUserId);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId}", randomUserId)).andExpect(status().isNotFound());
        //then
        verify(userService, times(1)).findOne(randomUserId);
    }
}
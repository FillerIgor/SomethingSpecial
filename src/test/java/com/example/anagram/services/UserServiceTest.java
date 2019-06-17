package com.example.anagram.services;

import com.example.anagram.repository.UserRepository;
import com.example.anagram.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService sut;

    @Test
    public void saveOldStyle() {
        //given
        //when
        //then
    }

}
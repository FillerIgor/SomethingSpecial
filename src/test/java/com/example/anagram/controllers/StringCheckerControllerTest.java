package com.example.anagram.controllers;

import com.example.anagram.services.StringCheckerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StringCheckerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @SpyBean
    StringCheckerService stringCheckerService;

    @Test
    public void should_return_true_if_anagram() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/?firstString=rail safety&secondString=fairy tales"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    @Test
    public void should_throw_exception() throws Exception {
        doThrow(new RuntimeException("My message")).when(stringCheckerService).isStringsAnagram(any(String.class), any(String.class));
        mockMvc.perform(MockMvcRequestBuilders.get("/?firstString=rail safety&secondString=fairy tales"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        verify(stringCheckerService, times(1)).isStringsAnagram(any(String.class), any(String.class));
    }

    @Test
    public void should_throw_exception_when_not_found_url() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
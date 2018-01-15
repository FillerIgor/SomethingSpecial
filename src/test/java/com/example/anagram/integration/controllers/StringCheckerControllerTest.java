package com.example.anagram.integration.controllers;

import com.example.anagram.AbstractMockMvc;
import com.example.anagram.unit.services.StringCheckerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class StringCheckerControllerTest extends AbstractMockMvc {

    @Autowired
    MockMvc mockMvc;

    @SpyBean(name = "stringCheckerService")
    StringCheckerService stringCheckerService;

    @Test
    public void should_return_true_if_anagram() throws Exception {
        //given
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/?firstString=rail safety&secondString=fairy tales"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("true"));
        //then
        verify(stringCheckerService, times(1)).isStringsAnagram(any(String.class), any(String.class));
    }

    @Test
    public void should_return_false_if_anagram() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/?firstString=rasafety&secondString=fairy tales"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        //then
        verify(stringCheckerService, times(1)).isStringsAnagram(any(String.class), any(String.class));
    }

    @Test
    public void should_throw_exception_when_not_found_url() throws Exception {
        //given
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
        //then
        verify(stringCheckerService, times(0)).isStringsAnagram(any(String.class), any(String.class));
    }
}
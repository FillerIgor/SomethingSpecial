package com.example.anagram.services;

import com.example.anagram.service.StringCheckerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StringCheckerServiceTest {

    @InjectMocks
    private StringCheckerService sut;

    @Test
    public void shouldReturnTrueWhenStringIsAnagram() {
        //given
        final String firstString = "rail safety";
        final String secondString = "fairy tales";
        //when
        boolean result = sut.isStringsAnagram(firstString, secondString);
        //then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenStringIsNotAnagram() {
        //given
        final String firstString = "first string";
        final String secondString = "second string";
        //when
        final boolean result = sut.isStringsAnagram(firstString, secondString);
        //then
        assertThat(result).isFalse();
    }
}
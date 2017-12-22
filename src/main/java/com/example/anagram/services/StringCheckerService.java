package com.example.anagram.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Profile("production")
public class StringCheckerService {

    Logger LOGGER = LoggerFactory.getLogger(StringCheckerService.class);

    public boolean isStringsAnagram(String firstString, String secondString) {
        LOGGER.error("I'm here");
        Map<Character, Long> firstStringCharToAmount = firstString.chars().mapToObj(value -> (char) value).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Character, Long> secondStringCharToAmount = secondString.chars().mapToObj(value -> (char) value).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        LOGGER.error("I'm here " + Objects.equals(firstStringCharToAmount, secondStringCharToAmount));
        return Objects.equals(firstStringCharToAmount, secondStringCharToAmount);
    }
}

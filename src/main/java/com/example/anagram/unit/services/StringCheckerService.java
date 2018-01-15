package com.example.anagram.unit.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StringCheckerService {

    Logger LOGGER = LoggerFactory.getLogger(StringCheckerService.class);

    public boolean isStringsAnagram(String firstString, String secondString) {
        Map<Character, Long> firstStringCharToAmount = firstString.chars().mapToObj(value -> (char) value).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Character, Long> secondStringCharToAmount = secondString.chars().mapToObj(value -> (char) value).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return Objects.equals(firstStringCharToAmount, secondStringCharToAmount);
    }
}

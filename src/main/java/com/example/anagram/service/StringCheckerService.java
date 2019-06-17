package com.example.anagram.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StringCheckerService {

    public boolean isStringsAnagram(String firstString, String secondString) {
        final Map<Character, Long> firstStringCharToAmount = firstString.chars().mapToObj(value -> (char) value).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        final Map<Character, Long> secondStringCharToAmount = secondString.chars().mapToObj(value -> (char) value).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return Objects.equals(firstStringCharToAmount, secondStringCharToAmount);
    }
}

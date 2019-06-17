package com.example.anagram.controller;

import com.example.anagram.service.StringCheckerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequiredArgsConstructor
public class StringCheckerController {

    private final StringCheckerService stringCheckerService;

    @GetMapping
    public boolean isStringsAnagram(HttpServletRequest request, @RequestParam String firstString, @RequestParam String secondString) {
        log.info("Calling {}", request.getRequestURL());
        return stringCheckerService.isStringsAnagram(firstString, secondString);
    }
}

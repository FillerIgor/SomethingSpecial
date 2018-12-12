package com.example.anagram.controllers;

import com.example.anagram.services.StringCheckerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@Slf4j
@RequiredArgsConstructor
public class StringCheckerController {

    private final StringCheckerService stringCheckerService;

    @GetMapping
    public boolean isStringsAnagram(HttpServletRequest request, @RequestParam String firstString, @RequestParam String secondString) {
        log.info("Calling {}", request.getRequestURL());
        return stringCheckerService.isStringsAnagram(firstString, secondString);
    }
}

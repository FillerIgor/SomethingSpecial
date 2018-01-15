package com.example.anagram.controllers;

import com.example.anagram.unit.services.StringCheckerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class StringCheckerController {
    private final static Logger LOGGER = LoggerFactory.getLogger(StringCheckerController.class);
    private final StringCheckerService stringCheckerService;

    @Autowired
    public StringCheckerController(StringCheckerService stringCheckerService) {
        this.stringCheckerService = stringCheckerService;
    }

    @GetMapping
    public boolean isStringsAnagram(HttpServletRequest request, @RequestParam String firstString, @RequestParam String secondString) {
        LOGGER.info("Calling" + request.getRequestURL());
        return stringCheckerService.isStringsAnagram(firstString, secondString);
    }
}

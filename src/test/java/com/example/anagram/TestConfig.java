package com.example.anagram;

import com.example.anagram.unit.services.StringCheckerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("test")
class TestConfig {

    @Bean
    StringCheckerService stringCheckerServiceForTest() {
        return new StringCheckerService();
    }

    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}

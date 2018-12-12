package com.example.anagram.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class StringCheckerServiceTest {
    @Autowired
    private StringCheckerService stringCheckerService;

    @Test
    public void isStringsAnagram() throws Exception {
        //given
        String firstString = "rail safety";
        String secondString = "fairy tales";
        //when
        boolean result = stringCheckerService.isStringsAnagram(firstString, secondString);
        //then
        assertThat(result).isTrue();
    }

    @Configuration
    @EnableConfigurationProperties
//    @Profile("prod")
    static class TestConfig {
        @Bean
        StringCheckerService stringCheckerService() {
            return new StringCheckerService();
        }
    }

}
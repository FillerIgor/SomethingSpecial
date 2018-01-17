package com.example.anagram.unit.services;

import com.example.anagram.AbstractJunitTestClass;
import com.example.anagram.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

public class UserServiceTest extends AbstractJunitTestClass {

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveOldStyle() throws Exception {
    }

    @Test
    public void getOldStyle() throws Exception {

    }

    @Test
    public void name() throws JsonProcessingException {
        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setFirstName("Igor");
        user.setLastName("Filler");
        user.setAge(24);
        String userAsString = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(user);

        assertThat(userAsString)
                .isNotBlank()
                .isNotEmpty()
                .containsSequence("\"firstName\" : \"Igor\"")
                .containsSequence("\"lastName\" : \"Filler\"")
                .containsSequence("\"age\" : 24");
    }
}
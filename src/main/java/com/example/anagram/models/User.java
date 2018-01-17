package com.example.anagram.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;

import java.util.UUID;

@Data
@Document(collection = "users")
public class User extends ResourceSupport {
    @Id
    private UUID userId;
    private String firstName;
    private String lastName;
    private int age;

    public static User getUserFromDocument(org.bson.Document document) {
        User user = new User();
        user.setUserId(UUID.fromString(document.getString("userId")));
        user.setFirstName(document.getString("firstName"));
        user.setLastName(document.getString("lastName"));
        user.setAge(document.getInteger("age"));
        return user;
    }
}
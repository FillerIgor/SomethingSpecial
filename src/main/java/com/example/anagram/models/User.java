package com.example.anagram.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "users")
public class User {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private int age;

    public static User getUserFromDocument(org.bson.Document document) {
        User user = new User();
        user.setId(UUID.fromString(document.getString("id")));
        user.setFirstName(document.getString("firstName"));
        user.setLastName(document.getString("lastName"));
        user.setAge(document.getInteger("age"));
        return user;
    }
}
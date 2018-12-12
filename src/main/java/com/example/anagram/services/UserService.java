package com.example.anagram.services;

import com.example.anagram.models.User;
import com.example.anagram.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final MongoClient mongoClient;

    public void saveOldStyle(User user) throws JsonProcessingException {
        MongoCollection<Document> users = mongoClient.getDatabase("mongodb").getCollection("users");
        String userAsJsonString = new ObjectMapper().writeValueAsString(user);
        users.insertOne(Document.parse(userAsJsonString));
    }

    public User findOne(String id) throws ObjectNotFoundException {
        MongoCollection<Document> users = mongoClient.getDatabase("mongodb").getCollection("users");
        Document userDocument = Optional.ofNullable(users.find(new Document("id", id)).first()).orElseThrow(() -> {
            log.error("User with id: {} is not found", id);
            return new ObjectNotFoundException(String.format("User with id: %s is not found", id));
        });
        return User.getUserFromDocument(userDocument);
    }
}

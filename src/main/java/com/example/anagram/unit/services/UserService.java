package com.example.anagram.unit.services;

import com.example.anagram.models.User;
import com.example.anagram.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import javassist.tools.rmi.ObjectNotFoundException;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;
    private final MongoClient mongoClient;

    @Autowired
    public UserService(UserRepository repository, MongoClient mongoClient) {
        this.repository = repository;
        this.mongoClient = mongoClient;
    }

    public void saveOldStyle(User user) throws JsonProcessingException {
        MongoCollection<Document> users = mongoClient.getDatabase("mongodb").getCollection("users");
        String userAsJsonString = new ObjectMapper().writeValueAsString(user);
        users.insertOne(Document.parse(userAsJsonString));
    }

    public User findOne(String id) throws ObjectNotFoundException, IOException {
        MongoCollection<Document> users = mongoClient.getDatabase("mongodb").getCollection("users");
        Document userDocument = Optional.ofNullable(users.find(new Document("id", id)).first())
                .orElseThrow(() -> new ObjectNotFoundException("User with id: " + id + " is not found"));
        return User.getUserFromDocument(userDocument);
    }
}

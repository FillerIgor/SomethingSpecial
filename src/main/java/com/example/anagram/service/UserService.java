package com.example.anagram.service;

import com.example.anagram.model.User;
import com.example.anagram.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MongoClient mongoClient;

    public User findOne(String uuid){
        return Optional.ofNullable(userRepository.findOne(UUID.fromString(uuid)))
                .orElseThrow(()-> new RuntimeException(String.format("There is no user with provided uuid: %s", uuid)));
    }
    //TODO remove if not needed more
    public User findOneOldStyle(String id) throws ObjectNotFoundException {
        MongoCollection<Document> users = mongoClient.getDatabase("mongodb").getCollection("users");
        Document userDocument = Optional.ofNullable(users.find(new Document("id", id)).first()).orElseThrow(() -> {
            log.error("User with id: {} is not found", id);
            return new ObjectNotFoundException(String.format("User with id: %s is not found", id));
        });
        return User.getUserFromDocument(userDocument);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    //TODO remove if not needed more
    public void saveOldStyle(User user) throws JsonProcessingException {
        MongoCollection<Document> users = mongoClient.getDatabase("mongodb").getCollection("users");
        String userAsJsonString = new ObjectMapper().writeValueAsString(user);
        users.insertOne(Document.parse(userAsJsonString));
    }

    public String saveUser(User user) {
        user.setId(UUID.randomUUID());
        User savedUser = userRepository.save(user);
        return savedUser.getId().toString();
    }

    public String updateUser(User user) {
        return userRepository.save(user).getId().toString();
    }

    public String deleteUserById(String uuid) {
        userRepository.delete(UUID.fromString(uuid));
        return uuid;
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
        log.info("All users are successfully deleted !");
    }
}

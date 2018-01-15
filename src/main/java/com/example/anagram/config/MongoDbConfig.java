package com.example.anagram.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Profile({"prod", "dev"})
public class MongoDbConfig {
    @Value("${spring.data.mongodb.host}")
    private String dbHost;
    @Value("${spring.data.mongodb.port}")
    private int dbPort;
    @Value("${spring.data.mongodb.database}")
    private String dbName;
    @Value("#{'${application.mongodb.collections}'.split(',')}")
    private List<String> collectionNames;// = Collections.singletonList("users");

    @Bean
    public MongoClient mongoClient() {
        MongoClient mongoClient = new MongoClient(dbHost, MongoClientOptions.builder().writeConcern(WriteConcern.ACKNOWLEDGED).build());
        MongoDatabase database = mongoClient.getDatabase(dbName);
        ArrayList existingCollections = database.listCollectionNames().into(new ArrayList());
        collectionNames.stream().filter(collection -> !existingCollections.contains(collection)).forEach(database::createCollection);
        return mongoClient;
    }
}

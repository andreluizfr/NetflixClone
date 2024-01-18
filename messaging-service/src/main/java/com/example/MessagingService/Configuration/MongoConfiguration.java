package com.example.MessagingService.Configuration;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfiguration extends AbstractMongoClientConfiguration {

    @Value("${mongo.username}")
    private String mongo_username;

    @Value("${mongo.password}")
    private String mongo_password;

    @Value("${mongo.server}")
    private String mongo_server;

    @Value("${mongo.database}")
    private String mongo_database;

    @Override
    protected String getDatabaseName() {
        return "netflixClone";
    }
 
    @Override
    public MongoClient mongoClient() {

        ConnectionString connectionString = new ConnectionString("mongodb://"
            + mongo_username + ":" 
            + mongo_password + "@" 
            + mongo_server + "/" 
            + mongo_database);

        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
        
        return MongoClients.create(mongoClientSettings);
    }
 
    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.example.MessagingService.DataProvider");
    }
}
package org.openapitools.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfiguration {

    @Bean
    public com.mongodb.client.MongoClient mongoClient(@Value("${spring.data.mongodb.uri:mongodb://localhost:27017}") String uri) {
        return MongoClients.create(uri);
    }

    @Bean
    public MongoDatabaseFactory mongoDbFactory(MongoClient mongoClient, @Value("${spring.data.mongodb.database:mydatabase}") String dbName) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, dbName);
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory) {
        return new MongoTemplate(mongoDbFactory);
    }
}

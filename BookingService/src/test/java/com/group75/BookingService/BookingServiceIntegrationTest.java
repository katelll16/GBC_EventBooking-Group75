package com.group75.BookingService.service;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest
public class BookingServiceIntegrationTest {

    static MongoDBContainer mongoDB = new MongoDBContainer("mongo:latest");

    @BeforeAll
    static void startContainer() {
        mongoDB.start();
    }

    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDB::getReplicaSetUrl);
    }
}

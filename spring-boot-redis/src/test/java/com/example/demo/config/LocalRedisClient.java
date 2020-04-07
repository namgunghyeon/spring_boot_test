package com.example.demo.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Configuration;

@TestConfiguration
public class LocalRedisClient {
    private static StatefulRedisConnection<String, String> connection;

    private static StatefulRedisConnection<String, String> connection() {
        if (connection == null) {
            RedisClient client = RedisClient.create("redis://localhost");
            connection = client.connect();
        }

        return connection;
    }

    public StatefulRedisConnection<String, String> getConnection() {
        return connection;
    }
}
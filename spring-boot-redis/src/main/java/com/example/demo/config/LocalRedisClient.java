package com.example.demo.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisStringAsyncCommands;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Configuration
public class LocalRedisClient {
    private static StatefulRedisConnection<String, String> connection;

    static {
        connection();
    }

    private static StatefulRedisConnection<String, String> connection() {
        if (connection == null) {
            RedisClient client = RedisClient.create("redis://localhost");
            connection = client.connect();

            System.out.println(connection);
        }

        return connection;
    }

    public StatefulRedisConnection<String, String> getConnection() {
        return connection;
    }
}

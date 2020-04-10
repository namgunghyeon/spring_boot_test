package com.example.demo.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class EmbeddedRedisConfig {
    private static RedisServer redisServer;

    public static void start() {
        redisServer = new RedisServer();
        redisServer.start();
    }

    public static void stop() {
        if (redisServer != null) {
            redisServer.stop();
        }

    }
}

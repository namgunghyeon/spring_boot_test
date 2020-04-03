package com.example.demo.service;

import com.example.demo.config.LocalRedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisStringAsyncCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class TestService {
    private final  Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LocalRedisClient localRedisClient;

    @Cacheable(cacheNames = "getData", key="#id")
    public String getData(String id) {
        logger.info("getData from db");
        return id;
    }

    public String get(String key) throws ExecutionException, InterruptedException {
        RedisStringAsyncCommands<String, String> async = localRedisClient.getConnection().async();
        RedisFuture<String> get = async.get(key);

        return get.get();
    }
}

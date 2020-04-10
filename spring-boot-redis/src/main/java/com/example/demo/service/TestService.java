package com.example.demo.service;

import com.example.demo.config.LocalRedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisStringAsyncCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class TestService {
    private final  Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private LocalRedisClient localRedisClient;
    @Autowired
    private RedisTemplate<String, String> redisTemplateString;
    @Autowired
    private RedisTemplate<String, Object> redisTemplateObject;

    @Cacheable(cacheNames = "getData", key="#id")
    public String getData(String id) {
        logger.info("getData from db");
        return id;
    }

    public String get(String key) throws ExecutionException, InterruptedException, TimeoutException {
        RedisStringAsyncCommands<String, String> async = localRedisClient.getConnection().async();
        RedisFuture<String> get = async.get(key);

        return get.get(1100, TimeUnit.MILLISECONDS);
    }

    public String get2(String key) {
        ValueOperations<String, String> vop = redisTemplateString.opsForValue();
        return vop.get(key);
    }

    public Object get3(String key) {
        ValueOperations<String, Object> vop = redisTemplateObject.opsForValue();
        return vop.get(key);
    }
}

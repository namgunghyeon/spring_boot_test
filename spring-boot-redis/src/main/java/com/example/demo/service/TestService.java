package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    private final  Logger logger = LoggerFactory.getLogger(getClass());

    @Cacheable(cacheNames = "getData", key="#id")
    public String getData(String id) {
        logger.info("getData from db");
        return id;
    }
}

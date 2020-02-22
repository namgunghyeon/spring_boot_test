package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("test")
public class TsetModel {
    @Id
    private String id;
}

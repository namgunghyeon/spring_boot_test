package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.repository.TestModelRedisRepository;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
public class RedisController {
    @Autowired
    private TestModelRedisRepository testModelRedisRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private TestService testService;

    @GetMapping(path="/redis")
    public ApiResponse<String> redis() {
        String data = stringRedisTemplate.opsForValue().get("test");

        return new ApiResponse<String>(data);
    }

    @GetMapping(path = "/cache")
    public String getData() {
        return testService.getData("test");
    }

    @GetMapping(path = "/test")
    public String test() throws ExecutionException, InterruptedException, TimeoutException {
        return testService.get("test");
    }
}

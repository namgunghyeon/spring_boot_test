package com.example.demo.controller;

import com.example.demo.model.Test;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(path = "/test")
    public List<Test> test() {
        return testService.getTest("test");
    }
}

package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(path="test1")
    public String test1() {
        return "test1";
    }

    @GetMapping(path="test2")
    public String test2() {
        return "test2";
    }

    @GetMapping(path="test3")
    public String test3() {
        return "test3";
    }
}

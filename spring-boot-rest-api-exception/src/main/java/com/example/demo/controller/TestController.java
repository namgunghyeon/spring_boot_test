package com.example.demo.controller;

import com.example.demo.dto.TestRequestDTO;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
public class TestController {
    @GetMapping(path = "/test-notfound")
    public void notfound() {
        throw new ResourceNotFoundException("not found 12");
    }

    @GetMapping(path = "/test-notfound/{id}/test")
    public void pathValue(@PathVariable String id) {
        throw new ResourceNotFoundException("not found " + id);
    }

    @PostMapping(path="/test")
    public TestRequestDTO test(@RequestBody @Valid TestRequestDTO testRequestDTO) {
        return testRequestDTO;
    }

    @GetMapping(path="/null-point")
    public TestRequestDTO nullPoint() {
        throw new NullPointerException("null point");
    }

    @GetMapping(path="/internal")
    public TestRequestDTO internal() throws Exception {
        throw new Exception("internal error");
    }
}

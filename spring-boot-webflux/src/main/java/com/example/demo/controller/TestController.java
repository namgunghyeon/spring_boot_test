package com.example.demo.controller;

import com.example.demo.dto.TestModelDTO;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(path="flux")
    public Flux<String> flux() {
        return Flux.just("string1", "string2", "string3");
    }

    @GetMapping(path="mono")
    public Mono<TestModelDTO> mono() throws IOException {
        return Mono.fromCallable(() -> TestModelDTO.convert(testService.get())).subscribeOn(Schedulers.elastic());
    }

    @GetMapping(path="/")
    public Mono<TestModelDTO> get() throws IOException {
      return testService.getMono();
    }
}

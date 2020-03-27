package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {
    private final RestTemplate restTemplate;

    public Controller(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/show")
    public String showTest(){
        return restTemplate.getForObject("http://client-example/test", String.class);
    }
}

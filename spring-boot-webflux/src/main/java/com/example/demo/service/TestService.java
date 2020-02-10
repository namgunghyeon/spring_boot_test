package com.example.demo.service;

import com.example.demo.dto.TestModelDTO;
import com.example.demo.model.TestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.stream.BaseStream;

@Service
public class TestService {
    @Autowired
    private ObjectMapper objectMapper;

    public TestModel get() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("test.json");

        return objectMapper.readValue(classPathResource.getInputStream(), TestModel.class);
    }

    public Mono<TestModelDTO> getMono() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("test.json");

        /*
            TODO: 파일 읽는 부분을 reactive 변경
         */
        TestModel testModel = objectMapper.readValue(classPathResource.getInputStream(), TestModel.class);
        TestModelDTO testModelDTO = TestModelDTO.convert(testModel);

        return Mono.just(testModelDTO);
    }
}

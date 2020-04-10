package com.example.demo;

import com.example.demo.repository.ServiceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


@SpringBootTest
public class RepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    void test() {
        userRepository.findByUsername("1");
    }
}

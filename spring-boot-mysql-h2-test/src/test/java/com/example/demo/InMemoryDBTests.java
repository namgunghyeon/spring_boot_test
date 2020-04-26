package com.example.demo;

import com.example.demo.config.StudentJpaConfig;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@SpringBootTest
@ContextConfiguration(
        classes = { StudentJpaConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
class InMemoryDBTests {
    @Resource
    private StudentRepository studentRepository;

    @Test
    public void givenStudent_whenSave_thenGetOk() {
        Student student = new Student(1, "john");
        studentRepository.save(student);

    }
}